package com.yjhh.ppwcustomer.view

import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.MainFinalDataBean

interface CouponView{

    fun onSuccess(main1bean: CouponBean, flag:String)

    fun onFault(errorMsg: String?)
}
