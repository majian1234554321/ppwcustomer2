package com.paipaiwei.takeout_personal.view



interface CommonView{
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}
