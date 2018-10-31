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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.resetpasswordfragment.*
import java.util.concurrent.TimeUnit

class ResetPasswordFragment : BaseFragment(), View.OnClickListener, RegistView {
    override fun registSuccess(date: LoginBean?) {

    }

    override fun registSuccess2(date: String?) {

    }

    override fun registFault(registFaultMessage: String) {

    }

    override fun sendSMSSuccess(date: LoginBean?) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, "验证码发送失败$message", Toast.LENGTH_SHORT).show()
    }

    val identity = "0"//身份（即客户端类型，0用户 1骑手 2商户）
    val TYPE = "21"//1登录 2注册 21 重置密码
    val refId = "";//推荐人ID/phone
    val MAX_COUNT_TIME = 5L

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutRes(): Int = R.layout.resetpasswordfragment

    override fun initView(rootView: View?) {

        val regByAccountPresent = RegByAccountPresent(context, this)
        bt_commit.setOnClickListener(this)

        tbv_title.setOnNavigation(NavigationOnClickListener {
            activity?.finish()
        })


        val disposable = RxView.clicks(tv_verifyCode)
            //防止重复点击
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val phone = et_phone.text.toString()
                if (TextUtils.isEmpty(phone) && phone.length == 11) {
                    Toast.makeText(activity, "手机号码不能为空", Toast.LENGTH_SHORT).show()
                    Observable.empty()
                } else {
                    Observable.just(true)
                }
            }
            .doOnNext {
                Log.i("TAG", "初始化")
                regByAccountPresent.sendSms(TYPE, et_phone.text.toString())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                RxView.enabled(tv_verifyCode).accept(false)
                RxTextView.text(tv_verifyCode).accept("剩余 $MAX_COUNT_TIME 秒")

                Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                    .take(MAX_COUNT_TIME)
                    //将递增数字替换成递减的倒计时数字
                    .map { aLong -> MAX_COUNT_TIME - (aLong + 1); }
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