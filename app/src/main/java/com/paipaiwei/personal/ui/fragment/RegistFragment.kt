package com.paipaiwei.personal.ui.fragment

import android.content.Intent
import androidx.core.content.ContextCompat
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseFragment


import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.common.utils.SpannableStringUtils
import com.paipaiwei.personal.present.RegByAccountPresent
import com.paipaiwei.personal.ui.activity.MainActivity
import com.paipaiwei.personal.view.RegistView
import com.yjhh.common.Constants.MAX_COUNT_TIME
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.registfragment.*
import org.json.JSONObject

import java.util.concurrent.TimeUnit


class RegistFragment : BaseFragment(), View.OnClickListener, RegistView {

    override fun getLayoutRes(): Int = R.layout.registfragment


    override fun registSuccess2(date: String?) {
        Toast.makeText(context, "用户注册成功", Toast.LENGTH_SHORT).show()

        val jsonObject = JSONObject(date)
        val mobile = jsonObject.getString("mobile")
        val nickName = jsonObject.getString("nickName")
        val sessionId = jsonObject.getString("sessionId")
        val type = jsonObject.getString("type")

        SharedPreferencesUtils.setParam(mActivity, "mobile", mobile)
        SharedPreferencesUtils.setParam(mActivity, "nickName", nickName)
        SharedPreferencesUtils.setParam(mActivity, "sessionId", sessionId)
        SharedPreferencesUtils.setParam(mActivity, "type", type)


        RxBus.default.post(LoginBean(mobile, true))
       // startActivity(Intent(mActivity, MainActivity::class.java))
        activity?.finish()
    }


    val TYPE = "2"
    val refId = "";//推荐人ID/phone

    override fun registSuccess(date: LoginBean?) {




        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show()



        // startActivity(Intent(mActivity, MainActivity::class.java))
        mActivity.finish()
    }

    override fun registFault(registFaultMessage: String) {
        Toast.makeText(context, "$registFaultMessage", Toast.LENGTH_SHORT).show()
    }

    override fun sendSMSSuccess(date: String?) {
        Toast.makeText(context, "验证码发送成功", Toast.LENGTH_SHORT).show()
        tv_verifyCode?.text = "发送验证码"
    }

    override fun sendSMSFault(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
        when (v?.id) {


            R.id.bt_register -> {


                if (checkbox.isChecked) {
                    if (et_phone.text.length == 11 && et_password.text.length >= 6 && et_verifyCode.text != null) {
                        regByAccountPresent.regByAccount2(
                            et_phone.text.toString()
                            , et_password.text.toString()
                            , et_verifyCode.text.toString()

                            , refId
                        )
                    } else {
                        Toast.makeText(context, "用户名、密码、验证码不符合要求", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(mActivity, "请同意协议", Toast.LENGTH_SHORT).show()
                }


            }

            else -> {
            }
        }
    }


    private lateinit var regByAccountPresent: RegByAccountPresent
    override fun initView() {

        regByAccountPresent = RegByAccountPresent(context, this)

        bt_register.setOnClickListener(this)
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
                    if (tv_verifyCode != null) {
                        RxView.enabled(tv_verifyCode).accept(true)
                        RxTextView.text(tv_verifyCode).accept("发送验证码")
                    }
                } else {
                    if (tv_verifyCode != null) {
                        RxTextView.text(tv_verifyCode).accept("剩余 $it 秒")
                    }


                }
                Log.i("TAG", it.toString())
            }
        compositeDisposable.add(disposable)


        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {


                checkbox.isChecked = false


            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ContextCompat.getColor(mActivity, R.color.colorPrimary)
                ds.isUnderlineText = true

            }
        }

        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                //ToastUtils.showShortToast("事件触发了 landscapes and nedes")


                checkbox.isChecked = false
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ContextCompat.getColor(mActivity, R.color.colorPrimary)
                ds.isUnderlineText = true
            }
        }


        val textvalue = "我已阅读并同意 "

        checkbox.text = SpannableStringUtils.getBuilder(textvalue).append("服务条款").setClickSpan(clickableSpan)
            .append(" 和 ").append("隐私政策").setClickSpan(clickableSpan2)
            .create()

        checkbox.movementMethod = LinkMovementMethod.getInstance()


    }


}