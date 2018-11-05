package com.yjhh.loginmodule.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.JsonObject

import org.json.JSONObject
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.loginmodule.R
import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.loginmodule.present.LoginPresent
import com.yjhh.loginmodule.view.LoginView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import kotlinx.android.synthetic.main.module_login_activity_login.*
import java.util.concurrent.TimeUnit

@Route(path = "/LoginActivity/Login")
class LoginActivity : BaseActivity(), LoginView, View.OnClickListener {


    val identity = "0"

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_show_pwd -> {
                if (et_password.inputType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    et_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    iv_show_pwd.setImageResource(R.drawable.pass_visuable)
                } else {
                    et_password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    iv_show_pwd.setImageResource(R.drawable.pass_gone)
                }
                val pwd = et_password.text.toString()
                if (!TextUtils.isEmpty(pwd))
                    et_password.setSelection(pwd.length)
            }

            R.id.regist -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "RegistFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.forget_password -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "ForgotPasswordFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.iv_close -> {
                finish()
            }
            else -> {
            }
        }
    }

    override fun onSuccess(result: LoginBean?) = Unit


    override fun onSuccess2(result: String?) {


        val jsonObject = JSONObject(result)

        val mobile = jsonObject.getString("mobile")
        val nickName = jsonObject.getString("nickName")
        val sessionId = jsonObject.getString("sessionId")
        val type = jsonObject.getString("type")

        SharedPreferencesUtils.setParam(this, "mobile", mobile)
        SharedPreferencesUtils.setParam(this, "nickName", nickName)
        SharedPreferencesUtils.setParam(this, "sessionId", sessionId)
        SharedPreferencesUtils.setParam(this, "type", type)

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()

        RxBus.default.post(LoginBean(mobile, true))

        finish()
    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)  // Start auto inject.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.module_login_activity_login)


        val observableName = RxTextView.textChanges(et_mobile)
        val observablePassword = RxTextView.textChanges(et_password)


        val disposable1 = RxView.clicks(btn_login)
            .throttleFirst(1, TimeUnit.SECONDS)
            .map {
                Observable.combineLatest(
                    observableName,
                    observablePassword,
                    BiFunction<CharSequence, CharSequence, JSONObject> { phone, password ->

                        JSONObject().put("phone", phone).put("password", password)
                            .put(
                                "flag", isPhoneValid(phone.toString()) && isPasswordValid(
                                    password.toString()
                                )
                            )
                    })
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i("TAG", it.blockingFirst().toString())
                if (it.blockingFirst().getString("flag")!!.toBoolean()) {

                    val loginPresent = LoginPresent(this, this)
                    loginPresent.login2(
                        it.blockingFirst().getString("phone"),
                        it.blockingFirst().getString("password"),
                        identity
                    )

                } else {

                    Toast.makeText(this, "账号密码信息错误", Toast.LENGTH_SHORT).show()
                }
            }



        iv_show_pwd.setOnClickListener(this)
        regist.setOnClickListener(this)
        forget_password.setOnClickListener(this)

        iv_close.setOnClickListener(this)


        iv_weChat.setOnClickListener(this)
        iv_sina.setOnClickListener(this)

        compositeDisposable.add(disposable1)


    }


    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 11
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }


}
