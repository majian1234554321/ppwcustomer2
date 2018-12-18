package com.paipaiwei.personal.present

import android.content.Context
import com.yjhh.common.api.ProcessObserver
import com.yjhh.common.api.ProcessObserver2


import com.yjhh.common.present.BasePresent
import com.paipaiwei.personal.bean.LoginBean

import com.paipaiwei.personal.model.LoginModel
import com.paipaiwei.personal.view.LoginView


class LoginPresent(var context: Context, var loginView: LoginView) : BasePresent() {
    private var loginModel: LoginModel = LoginModel()


    fun login(username: String, password: String, identity: String) {

        toSubscribe(loginModel.login(username, password), object : ProcessObserver<LoginBean>(context) {
            override fun onSuccess(data: LoginBean?) {
                loginView.onSuccess(data)
            }

            override fun onFault(message: String) {
                loginView.onFault(message)
            }

        })


    }


    fun login2(username: String, password: String) {


        toSubscribe2(loginModel.login2(username, password), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                    loginView.onSuccess2(response)
            }


            override fun onFault(message: String) {
                loginView.onFault(message)
            }

        })


    }


}