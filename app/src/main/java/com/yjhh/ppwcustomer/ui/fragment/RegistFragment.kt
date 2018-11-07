package com.yjhh.ppwcustomer.ui.fragment

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.NavigationOnClickListener
import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.loginmodule.present.RegByAccountPresent
import com.yjhh.loginmodule.view.RegistView
import com.yjhh.ppwcustomer.R
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.registfragment.*

import java.util.concurrent.TimeUnit


class RegistFragment : BaseFragment(), View.OnClickListener, RegistView, NavigationOnClickListener {

    override fun getLayoutRes(): Int = R.layout.registfragment
    override fun OnNavigation() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registSuccess2(date: String?) {
        Toast.makeText(context, "用户注册成功", Toast.LENGTH_SHORT).show()
        activity?.finish()
    }

    val identity = "0"//身份（即客户端类型，0用户 1骑手 2商户）
    val TYPE = "2"
    val refId = "";//推荐人ID/phone

    override fun registSuccess(date: LoginBean?) {

        activity?.finish()
    }

    override fun registFault(registFaultMessage: String) {
        Toast.makeText(context, "用户注册失败$registFaultMessage", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSSuccess(date: LoginBean?) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, "验证码发送失败$message", Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when (v?.id) {


            R.id.bt_register -> {

                if (et_phone.text.length == 11 && et_password.text.length >= 6 && et_verifyCode.text != null) {
                    regByAccountPresent.regByAccount2(
                        et_phone.text.toString()
                        , et_password.text.toString()
                        , et_verifyCode.text.toString()
                        , identity
                        , refId
                    )
                } else {
                    Toast.makeText(context, "用户名、密码、验证码不符合要求", Toast.LENGTH_SHORT).show()
                }

            }

            else -> {
            }
        }
    }




    val MAX_COUNT_TIME = 5L
    private lateinit var regByAccountPresent: RegByAccountPresent
    override fun initView() {


        regByAccountPresent = RegByAccountPresent(context, this)


        bt_register.setOnClickListener(this)
        tbv_title.setOnNavigation(NavigationOnClickListener {
            activity?.finish()
        })

        val disposable = RxView.clicks(tv_verifyCode)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val phone = et_phone.text.toString()
                if (!TextUtils.isEmpty(phone) && phone.length == 11) {
                    Observable.just(true)
                } else {
                    Toast.makeText(activity, "手机号码不符合要求", Toast.LENGTH_SHORT).show()
                    Observable.empty()
                }
            }

            .doOnNext {
                if (it) {
                    Log.i("TAG", "初始化")
                    regByAccountPresent.sendSms(TYPE, et_phone.text.toString())
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                if (it) {
                    RxView.enabled(tv_verifyCode).accept(false)
                    RxTextView.text(tv_verifyCode).accept("剩余 $MAX_COUNT_TIME 秒")

                    Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                        .take(MAX_COUNT_TIME)
                        .map { aLong -> MAX_COUNT_TIME - (aLong + 1); }
                } else {
                    Observable.just(0L)
                }


            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it == 0L) {
                    RxView.enabled(tv_verifyCode).accept(true)
                    RxTextView.text(tv_verifyCode).accept("发送验证码")
                } else {
                    RxTextView.text(tv_verifyCode).accept("剩余 $it 秒")

                }
                Log.i("TAG", it.toString())
            }

        compositeDisposable.add(disposable)


    }


}