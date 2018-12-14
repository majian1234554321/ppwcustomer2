package com.paipaiwei.takeout_personal.view

import com.yjhh.common.base.BaseView
import com.paipaiwei.takeout_personal.bean.MainFinalDataBean
import com.paipaiwei.takeout_personal.bean.RecentlyBrowseBean

interface RecentlyBrowseView : BaseView {
    fun onSuccess(main1bean: RecentlyBrowseBean, flag: String)

    override fun onFault(errorMsg: String?)
}