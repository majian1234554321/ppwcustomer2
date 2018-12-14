package com.yjhh.ppwbusiness.iview

import com.paipaiwei.takeout_personal.view.SendSMSView

interface PasswordView : SendSMSView {
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}