package com.ppwc.restaurant.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.ppwc.restaurant.apis.MeiShiService
import com.yjhh.common.api.QueryService
import com.ppwc.restaurant.bean.MeiShiFootBean
import com.ppwc.restaurant.iview.MeiShiHeadView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class QueryModelDataPresent(var context: Context, var view: MeiShiHeadView) : BasePresent() {





    fun meishiData(
        keyword: String,
        code: String,
        subCode: String,
        all: String,
        one: String,
        sort: String,
        query: String,
        pageIndex: Int,
        pageSize: Int,
        flag:String
    ) {
        map.clear()

        map["keyword"] = keyword
        map["code"] = code

        map["subCode"] = subCode


        map["all"] = all
        map["one"] = one
        map["sort"] = sort
        map["query"] = query
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()


        toSubscribe2(
            ApiServices.getInstance().create(MeiShiService::class.java).meishiData(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("meishi", response)
                    val model = gson.fromJson<MeiShiFootBean>(response, MeiShiFootBean::class.java)
                    view.MeiShiFootValue(model,flag)
                }

                override fun onFault(message: String) {
                    Log.i("meishi", message)
                }

            })
    }





}