package com.yjhh.ppwcustomer.view

import com.yjhh.ppwcustomer.bean.MyMessageBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean


interface  MyMessageView {
    fun onSuccess(main1bean: MyMessageBean, flag: String)

     fun onFault(errorMsg: String?)
}
