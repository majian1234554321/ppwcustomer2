package com.yjhh.common.iview



interface CommonView{
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}
