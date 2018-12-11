package com.yjhh.ppwcustomer.present

import android.content.Context
import com.yjhh.common.api.ProcessObserver
import com.yjhh.common.api.ProcessObserver2


import com.yjhh.common.present.BasePresent
import com.yjhh.ppwcustomer.bean.LoginBean

import com.yjhh.ppwcustomer.model.LoginModel
import com.yjhh.ppwcustomer.view.LoginView


class LoginPresent(var context: Context, var loginView: LoginView) : BasePresent() {
    private var loginModel: LoginModel = LoginModel()


    fun login(username: String, password: String, identity: String) {

        toSubscribe(loginModel.login(username, password, identity), object : ProcessObserver<LoginBean>(context) {
            override fun onSuccess(data: LoginBean?) {
                loginView.onSuccess(data)
            }

            override fun onFault(message: String) {
                loginView.onFault(message)
            }

        })


    }


    fun login2(username: String, password: String, identity: String) {


        toSubscribe2(loginModel.login2(username, password, identity), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                    loginView.onSuccess2(response)
            }


            override fun onFault(message: String) {
                loginView.onFault(message)
            }

        })


    }


}