package com.yjhh.ppwcustomer.view



interface CommonView{
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}
