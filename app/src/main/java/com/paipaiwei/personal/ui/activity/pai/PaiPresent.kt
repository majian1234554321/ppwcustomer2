package com.paipaiwei.personal.ui.activity.pai

import android.content.Context
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class PaiPresent(var context: Context, var view: PaiService.PaiView) : BasePresent() {
    fun qiangPai(type: String, pageIndex: Int, pageSize: Int, flag: String) {
        map.clear()
        map["type"] = type
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()

        toSubscribe2(ApiServices.getInstance().create(PaiService::class.java).qiangPai(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.PaiValue(response, flag)
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })

    }
}