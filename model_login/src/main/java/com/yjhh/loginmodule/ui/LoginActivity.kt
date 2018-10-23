package com.yjhh.loginmodule.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.widget.Toast

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.JsonObject

import org.json.JSONObject
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.common.base.BaseActivity
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
class LoginActivity : BaseActivity(), LoginView {
    override fun onSuccess(result: LoginBean?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @Autowired
    @JvmField
    var name: String? = null
    @Autowired
    @JvmField
    var age: Int? = 0

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
                        //                        isPhoneValid(phone.toString()) && isPasswordValid(
//                            password.toString()
//                        )
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
                    loginPresent.login("A", "B")

                    Log.i("TAG", it.blockingFirst().toString())
                } else {
                    Toast.makeText(this, "登录信息错误", Toast.LENGTH_SHORT).show()
                }
            }


        val disposable2 = RxView.clicks(iv_show_pwd).subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
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

        compositeDisposable.add(disposable1)
        compositeDisposable.add(disposable2)

//        ARouter.getInstance()
//            .build("/splashactivity/splash")
//            .withString("name", "老王")
//            .withInt("age", 23)
//            .navigation()


    }


    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 11
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }


}
