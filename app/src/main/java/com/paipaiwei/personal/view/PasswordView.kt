package com.paipaiwei.personal.view



interface PasswordView : SendSMSView {
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}