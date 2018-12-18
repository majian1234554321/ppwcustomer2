package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.MainFinalDataBean

interface CouponView{

    fun onSuccess(main1bean: CouponBean, flag:String)

    fun onFault(errorMsg: String?)
}
