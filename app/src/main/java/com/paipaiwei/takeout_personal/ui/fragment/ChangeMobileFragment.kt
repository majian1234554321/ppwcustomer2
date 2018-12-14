package com.paipaiwei.takeout_personal.ui.fragment

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tencent.mm.opensdk.utils.Log
import com.yjhh.common.base.BaseFragment

import com.paipaiwei.takeout_personal.present.RegByAccountPresent

import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.bean.LoginBean
import com.paipaiwei.takeout_personal.present.SectionUserPresent
import com.paipaiwei.takeout_personal.view.RegistView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.changemobilefragment.*
import java.util.concurrent.TimeUnit

class ChangeMobileFragment : BaseFragment(), RegistView, View.OnClickListener {
    override fun registSuccess(date: LoginBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registSuccess2(date: String?) {
        loginOut()
        mActivity.finish()
    }

    override fun registFault(registFaultMessage: String) {
        Toast.makeText(mActivity, registFaultMessage, Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSSuccess(date: LoginBean?) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, "验证码发送失败", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutRes(): Int = R.layout.changemobilefragment
    val TYPE = "3"//1登录 2注册 3绑定手机 21重置密码 22忘记密码
    val MAX_COUNT_TIME = 5L
    private lateinit var regByAccountPresent: RegByAccountPresent
    override fun initView() {


        regByAccountPresent = RegByAccountPresent(context, this)

        val disposable = RxView.clicks(tv_verifyCode)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val et_beforePhone = et_beforePhone.text.toString()

                val et_newPhone = et_newPhone.text.toString()


                if (!TextUtils.isEmpty(et_beforePhone) && et_beforePhone.length == 11 && !TextUtils.isEmpty(et_newPhone) && et_newPhone.length == 11) {
                    Observable.just(true)
                } else {
                    Toast.makeText(activity, "手机号码不符合要求", Toast.LENGTH_SHORT).show()
                    Observable.empty()
                }
            }

            .doOnNext {
                if (it) {
                    Log.i("TAG", "初始化")
                    //regByAccountPresent.sendSms(TYPE, et_newPhone.text.toString())
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

        bt_register.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.bt_register -> {
                if (et_beforePhone.text.length == 11 && et_newPhone.text.length == 11 && et_verifyCode.text != null) {
                    val present = SectionUserPresent(mActivity, this)

                    present.setMobile(
                        et_beforePhone.text.toString()
                        , et_newPhone.text.toString()
                        , et_verifyCode.text.toString()
                    )
                } else {
                    Toast.makeText(context, "用户名、密码、验证码不符合要求", Toast.LENGTH_SHORT).show()
                }

            }

            else -> {
            }
        }
    }

}