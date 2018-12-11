package com.yjhh.ppwcustomer.view

import com.yjhh.ppwcustomer.bean.LoginBean


interface LoginView {
    abstract fun onSuccess(result: LoginBean?)

    abstract fun onFault(errorMsg: String?)

    abstract fun onSuccess2(result: String?)
}