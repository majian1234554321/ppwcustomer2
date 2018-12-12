package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwcustomer.view.SendSMSView

interface PasswordView : SendSMSView {
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}