package com.paipaiwei.personal.view



interface CommonView{
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}
