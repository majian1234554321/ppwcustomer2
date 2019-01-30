package com.paipaiwei.personal.ui.activity.login

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.BaseApplication
import com.yjhh.common.Constants.MAX_COUNT_TIME
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.iview.PasswordView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.present.PasswordPresent
import com.paipaiwei.personal.ui.activity.MainActivity
import com.paipaiwei.personal.ui.fragment.BackViewFragment
import com.yjhh.common.api.CommonService
import com.yjhh.common.utils.RxBus

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.loginsmsfragment.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class LoginSMSFragment : BaseFragment(), PasswordView, View.OnClickListener {

    var sendSMS = true;

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {

                present?.fromSms(
                    et_mobile.text.toString()
                    , et_verifyCode.text.toString()

                    , "Android"
                )

            }
            R.id.loginPassword -> {
                mActivity.onBackPressed()
            }

            R.id.tv_kaidian -> {
                ApiServices.getInstance().create(CommonService::class.java)
                    .init()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(mActivity) {
                        override fun processValue(response: String?) {
                            Log.i("01018", response)
                            start(BackViewFragment.newInstance(JSONObject(response).optString("applyShopUrl")))

                        }

                        override fun onFault(message: String) {
                            Log.i("01018", message)
                        }
                    })
            }
            else -> {
            }
        }
    }

    override fun onSuccess(value: String?) {
        val jsonObject = JSONObject(value)

        val mobile = jsonObject.getString("mobile")
        val nickName = jsonObject.getString("nickName")
        val sessionId = jsonObject.getString("sessionId")
        val type = jsonObject.getString("type")

        SharedPreferencesUtils.setParam(mActivity, "mobile", mobile)
        SharedPreferencesUtils.setParam(mActivity, "nickName", nickName)
        SharedPreferencesUtils.setParam(mActivity, "sessionId", sessionId)
        SharedPreferencesUtils.setParam(mActivity, "type", type)

        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show()

        RxBus.default.post(LoginBean(mobile, true))

        // startActivity(Intent(mActivity, MainActivity::class.java))
        mActivity.finish()
    }

    override fun onFault(errorMsg: String?) {
    }

    override fun onSuccessSMS(value: String?) {
        Toast.makeText(BaseApplication.context, "验证码发送成功", Toast.LENGTH_SHORT).show()
        compositeDisposable.clear()
    }

    override fun onFaultSMS(errorMsg: String?) {
        Toast.makeText(BaseApplication.context, "验证码发送失败$errorMsg", Toast.LENGTH_SHORT).show()
    }


    val TYPE = "1"//1登录 2注册 21 重置密码 22找回密码


    var present: PasswordPresent? = null

    override fun getLayoutRes(): Int = R.layout.loginsmsfragment

    override fun initView() {

        arrayOf(loginPassword, btn_login, tv_kaidian).forEach {
            it.setOnClickListener(this)
        }



        present = PasswordPresent(context, this)


        val disposable = RxView.clicks(tv_verifyCode)
            //防止重复点击
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val phone = et_mobile.text.toString()
                if (!TextUtils.isEmpty(phone) && phone.length == 11) {
                    Observable.just(true)
                } else {
                    Toast.makeText(mActivity, "手机号码不符合要求", Toast.LENGTH_SHORT).show()
                    Observable.empty()
                }
            }
            .doOnNext {
                Log.i("TAG", "初始化")
                if (it) {
                    sendSMS = present?.sendSms(TYPE, et_mobile.text.toString())!!
                }

                sendSMS

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


    }


}