package com.yjhh.ppwcustomer.view

import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.IntegralBean

interface IntegralView{
    fun onSuccess(value : IntegralBean, flag:String)

    fun onFault(errorMsg: String?)
}
