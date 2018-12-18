package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.IntegralBean

interface IntegralView{
    fun onSuccess(value : IntegralBean, flag:String)

    fun onFault(errorMsg: String?)
}
