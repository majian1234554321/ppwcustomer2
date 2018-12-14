package com.paipaiwei.takeout_personal.view



interface SendSMSView{
    fun onSuccessSMS(value: String?)

    fun onFaultSMS(errorMsg: String?)
}
