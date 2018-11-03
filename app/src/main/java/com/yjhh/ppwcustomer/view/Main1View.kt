package com.yjhh.ppwcustomer.view

import com.yjhh.ppwcustomer.bean.Main1HeadBean
import com.yjhh.ppwcustomer.bean.MainFinalDataBean

interface Main1View {
    fun onSuccess(main1bean: MainFinalDataBean)

     fun onFault(errorMsg: String?)
}