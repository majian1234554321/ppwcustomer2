package com.yjhh.loginmodule.present

import android.content.Context
import com.yjhh.common.api.OnSuccessAndFaultSub
import com.yjhh.common.listener.OnSuccessAndFaultListener

import com.yjhh.common.present.BasePresent
import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.loginmodule.model.LoginModel
import com.yjhh.loginmodule.view.LoginView




class LoginPresent(var context: Context, var loginView: LoginView) : BasePresent() {
    private var loginModel: LoginModel = LoginModel()


    fun login(username: String, password: String) {
        toSubscribe(loginModel.login(username, password), OnSuccessAndFaultSub<LoginBean>(object :
            OnSuccessAndFaultListener<LoginBean> {
            override fun onSuccess(result: LoginBean?) {
                loginView.onSuccess(result)
            }

            override fun onFault(errorMsg: String?) {
                loginView.onFault(errorMsg)
            }

        }))

    }


}