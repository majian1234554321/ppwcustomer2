package com.yjhh.ppwbusiness.iview

import com.paipaiwei.personal.view.SendSMSView

interface PasswordView : SendSMSView {
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}