package com.paipaiwei.takeout_personal.view

import com.paipaiwei.takeout_personal.bean.LoginBean


interface LoginView {
    abstract fun onSuccess(result: LoginBean?)

    abstract fun onFault(errorMsg: String?)

    abstract fun onSuccess2(result: String?)
}