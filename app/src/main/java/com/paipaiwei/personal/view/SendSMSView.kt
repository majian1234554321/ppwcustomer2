package com.paipaiwei.personal.view



interface SendSMSView{
    fun onSuccessSMS(value: String?)

    fun onFaultSMS(errorMsg: String?)
}
