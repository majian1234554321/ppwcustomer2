package com.paipaiwei.takeout_personal.view

import com.paipaiwei.takeout_personal.bean.CouponBean
import com.paipaiwei.takeout_personal.bean.IntegralBean

interface IntegralView{
    fun onSuccess(value : IntegralBean, flag:String)

    fun onFault(errorMsg: String?)
}
