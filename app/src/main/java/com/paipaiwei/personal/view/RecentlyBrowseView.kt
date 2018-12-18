package com.paipaiwei.personal.view

import com.yjhh.common.base.BaseView
import com.paipaiwei.personal.bean.MainFinalDataBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean

interface RecentlyBrowseView : BaseView {
    fun onSuccess(main1bean: RecentlyBrowseBean, flag: String)

    override fun onFault(errorMsg: String?)
}