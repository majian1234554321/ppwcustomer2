package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.MyMessageBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean


interface  MyMessageView {
    fun onSuccess(main1bean: MyMessageBean, flag: String)

     fun onFault(errorMsg: String?)
}
