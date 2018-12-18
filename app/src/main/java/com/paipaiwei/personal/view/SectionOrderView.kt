package com.paipaiwei.personal.view

import android.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionOrderService
import com.paipaiwei.personal.bean.MainFinalDataBean
import io.reactivex.Observable
import okhttp3.ResponseBody

interface SectionOrderView {

    fun onSuccess(main1bean: MainFinalDataBean, flag:String)

    fun onFault(errorMsg: String?)

}