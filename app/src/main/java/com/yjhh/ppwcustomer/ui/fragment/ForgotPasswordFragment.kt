package com.yjhh.ppwcustomer.ui.fragment

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.LeftOnClickListener

import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.loginmodule.present.RegByAccountPresent
import com.yjhh.loginmodule.view.RegistView
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.present.SectionUserPresent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.forgotpasswordfragment.*
import java.util.concurrent.TimeUnit

class ForgotPasswordFragment : BaseFragment(), View.OnClickListener, RegistView {


    override fun getLayoutRes(): Int = R.layout.forgotpasswordfragment
    override fun registSuccess(date: LoginBean?) = Unit

    override fun registSuccess2(date: String?) {
        Toast.makeText(context, "重置密码成功", Toast.LENGTH_SHORT).show()
        activity?.finish()

    }

    override fun registFault(registFaultMessage: String) {
        Toast.makeText(context, "重置密码失败$registFaultMessage", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSSuccess(date: LoginBean?) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, "验证码发送失败$message", Toast.LENGTH_SHORT).show()
    }

    val identity = "0"//身份（即客户端类型，0用户 1骑手 2商户）
    val TYPE = "22"//1登录 2注册 21 重置密码 22找回密码
    val refId = "";//推荐人ID/phone
    val MAX_COUNT_TIME = 5L

    override fun onClick(v: View?) {

        if (et_phone.text.length == 11 && et_password.text.length >= 6 && et_verifyCode.text != null) {
            val present = SectionUserPresent(context, this)
            present.forgotPassword(et_phone.text.toString(), et_password.text.toString(), et_verifyCode.text.toString())
        } else {
            Toast.makeText(context, "手机号、密码、验证码不符合要求", Toast.LENGTH_SHORT).show()
        }

    }


    override fun initView() {

        val regByAccountPresent = RegByAccountPresent(context, this)
        bt_commit.setOnClickListener(this)




        val disposable = RxView.clicks(tv_verifyCode)
            //防止重复点击
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
                Log.i("TAG", "初始化")
                if (it) {
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
                        //将递增数字替换成递减的倒计时数字
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