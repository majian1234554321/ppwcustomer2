package com.yjhh.ppwcustomer.present

import android.content.Context

import com.yjhh.ppwcustomer.api.OnSuccessAndFaultSub
import com.yjhh.ppwcustomer.bean.LoginBean
import com.yjhh.ppwcustomer.listener.OnSuccessAndFaultListener
import com.yjhh.ppwcustomer.model.LoginModel
import com.yjhh.ppwcustomer.view.LoginView


class LoginPresent(var context: Context, var loginView: LoginView) : BasePresent() {
    private var loginModel: LoginModel = LoginModel()


    fun login(username: String, password: String) {
        toSubscribe(loginModel.login(username, password), OnSuccessAndFaultSub<LoginBean>(object : OnSuccessAndFaultListener<LoginBean> {
            override fun onSuccess(result: LoginBean?) {
                loginView.onSuccess(result)
            }

            override fun onFault(errorMsg: String?) {
                loginView.onFault(errorMsg)
            }

        }))

    }


}