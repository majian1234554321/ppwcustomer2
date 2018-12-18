package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.Main1HeadBean
import com.paipaiwei.personal.bean.MainFinalDataBean

interface Main1View {
    fun onSuccess(main1bean: MainFinalDataBean,flag:String)

     fun onFault(errorMsg: String?)
}