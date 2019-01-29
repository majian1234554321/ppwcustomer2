package com.yjhh.common.iview

import com.yjhh.common.base.BaseView


interface CommonView{
    fun onSuccess(value: String?,flag:String?)

    fun onFault(errorMsg: String?,flag: String?)
}
