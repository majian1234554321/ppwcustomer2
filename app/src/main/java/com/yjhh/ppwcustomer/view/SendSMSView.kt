package com.yjhh.ppwcustomer.view



interface SendSMSView{
    fun onSuccessSMS(value: String?)

    fun onFaultSMS(errorMsg: String?)
}
