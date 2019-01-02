package com.paipaiwei.personal.present

import android.content.Context
import android.util.ArrayMap
import com.paipaiwei.personal.apis.SearchInfoService
import com.paipaiwei.personal.bean.SearchInfoBean
import com.paipaiwei.personal.view.SearchInfoView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class SearchInfoPresent(var context: Context,var view : SearchInfoView) : BasePresent() {
    val map = ArrayMap<String, String>()


    fun searchHot() {
        toSubscribe2(ApiServices.getInstance().create(SearchInfoService::class.java).searchHot(),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    val mode = gson.fromJson<SearchInfoBean>(response,SearchInfoBean::class.java)
                    view.SearchInfoValue(mode)

                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })
    }

}