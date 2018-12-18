package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.LoginBean


interface LoginView {
    abstract fun onSuccess(result: LoginBean?)

    abstract fun onFault(errorMsg: String?)

    abstract fun onSuccess2(result: String?)
}