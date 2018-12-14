package com.paipaiwei.takeout_personal.view

import com.paipaiwei.takeout_personal.bean.RecentlyBrowseBean
import com.paipaiwei.takeout_personal.bean.UserinfoBean

interface UserInfoView{
    fun onSuccess(main1bean: UserinfoBean)

     fun onFault(errorMsg: String?)
}
