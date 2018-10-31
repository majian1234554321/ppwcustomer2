package com.yjhh.loginmodule.view

import com.yjhh.loginmodule.bean.LoginBean

interface LoginView {
    abstract fun onSuccess(result: LoginBean?)

    abstract fun onFault(errorMsg: String?)

    abstract fun onSuccess2(result: String?)
}