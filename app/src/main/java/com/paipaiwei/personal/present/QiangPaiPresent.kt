package com.paipaiwei.personal.present

import android.content.Context
import android.util.Log
import com.paipaiwei.personal.apis.QiangPaiService
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class QiangPaiPresent(var context: Context, var view: QiangPaiService.QiangPaiView) : BasePresent() {
    fun qiangPaiList(type: String?, pageIndex: Int, pageSize: Int, flag: String) {
        map.clear()
        map["type"] = type
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        toSubscribe2(ApiServices.getInstance().create(QiangPaiService::class.java).qiangPaiList(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.qiangPaiValue(response, flag)
                    Log.i("qiangPaiList", response)
                }

                override fun onFault(message: String) {
                    view.onFault(message)

                    Log.i("qiangPaiList", message)
                }

            })
    }


    fun detail(id: String?, flag: String) {
        map.clear()
        map["id"] = id

        toSubscribe2(ApiServices.getInstance().create(QiangPaiService::class.java).detail(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.qiangPaiValue(response, flag)
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })
    }


    fun qiangPai(id: String?, type: String?, flag: String) {
        map.clear()
        map["id"] = id


        toSubscribe2(ApiServices.getInstance().create(QiangPaiService::class.java).qiangPai(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.qiangPaiValue(response, flag)
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })
    }

}