package com.paipaiwei.takeout_personal.view

import com.paipaiwei.takeout_personal.bean.CouponBean
import com.paipaiwei.takeout_personal.bean.MainFinalDataBean

interface CouponView{

    fun onSuccess(main1bean: CouponBean, flag:String)

    fun onFault(errorMsg: String?)
}
