package com.yjhh.ppwcustomer.ui.activity.login


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import org.json.JSONObject
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.yjhh.common.Constants
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.ppwcustomer.R


import com.yjhh.ppwcustomer.present.LoginPresent

import com.yjhh.ppwcustomer.bean.LoginBean
import com.yjhh.ppwcustomer.view.LoginView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.module_login_activity_login.*


import java.util.concurrent.TimeUnit

@Route(path = "/LoginActivity/Login")
class LoginActivity : BaseActivity(), LoginView, View.OnClickListener {
    override fun onSuccess(result: LoginBean?) = Unit


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


            R.id.iv_weChat -> {

                val api = WXAPIFactory.createWXAPI(this, Constants.APP_ID_WX, true)

                api.registerApp(Constants.APP_ID_WX);


                val req =  SendAuth.Req();
                req.scope = "snsapi_userinfo";//
//                req.scope = "snsapi_login";//提示 scope参数错误，或者没有scope权限
                req.state = "wechat_sdk_微信登录";
                api.sendReq(req);


            }


            R.id.iv_sina -> {
                // finish()
            }


            else -> {
            }
        }
    }




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


        val view = arrayOf(iv_show_pwd, regist, forget_password, iv_close, iv_weChat, iv_sina)


        view.forEach {
            it.setOnClickListener(this)
        }




        compositeDisposable.add(disposable1)


    }


    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 11
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (mSsoHandler != null) {
//            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
//        }
    }




}


