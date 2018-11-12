package com.yjhh.ppwcustomer.view

import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean
import com.yjhh.ppwcustomer.bean.UserinfoBean

interface UserInfoView{
    fun onSuccess(main1bean: UserinfoBean)

     fun onFault(errorMsg: String?)
}
