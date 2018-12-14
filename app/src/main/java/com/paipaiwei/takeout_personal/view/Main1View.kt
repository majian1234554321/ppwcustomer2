package com.paipaiwei.takeout_personal.view

import com.paipaiwei.takeout_personal.bean.Main1HeadBean
import com.paipaiwei.takeout_personal.bean.MainFinalDataBean

interface Main1View {
    fun onSuccess(main1bean: MainFinalDataBean,flag:String)

     fun onFault(errorMsg: String?)
}