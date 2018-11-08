package com.yjhh.ppwcustomer.view

import com.yjhh.common.base.BaseView
import com.yjhh.ppwcustomer.bean.MainFinalDataBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean

interface RecentlyBrowseView : BaseView {
    fun onSuccess(main1bean: RecentlyBrowseBean, flag: String)

    override fun onFault(errorMsg: String?)
}