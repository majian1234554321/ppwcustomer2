package com.paipaiwei.takeout_personal.view

import com.paipaiwei.takeout_personal.bean.MyMessageBean
import com.paipaiwei.takeout_personal.bean.RecentlyBrowseBean


interface  MyMessageView {
    fun onSuccess(main1bean: MyMessageBean, flag: String)

     fun onFault(errorMsg: String?)
}
