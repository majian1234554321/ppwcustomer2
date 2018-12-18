package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.paipaiwei.personal.bean.UserinfoBean

interface UserInfoView{
    fun onSuccess(main1bean: UserinfoBean)

     fun onFault(errorMsg: String?)
}
