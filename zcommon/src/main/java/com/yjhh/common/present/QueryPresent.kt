package com.yjhh.common.present

import android.content.Context
import android.util.ArrayMap
import android.util.Log

import com.yjhh.common.api.QueryService

import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.bean.QueryResultBean
import com.yjhh.common.iview.QueryResultView
import com.yjhh.common.present.BasePresent

class QueryPresent(var context: Context,var view :QueryResultView) : BasePresent() {






    fun queryValue(
        keyword: String,
        code: String,
        one: String,
        sort: String,
        query: String,
        distance: String,
        pageIndex: Int,
        pageSize: Int
    ) {

        map.clear()
        map["keyword"] = keyword
        map["code"] = code
        map["one"] = one
        map["sort"] = sort
        map["query"] = query
        map["distance"] = distance
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()



        toSubscribe2(
            ApiServices.getInstance().create(QueryService::class.java).searchResult(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("meishi", response)
                    val model = gson.fromJson<QueryResultBean>(response, QueryResultBean::class.java)
                    view.queryResultValue(model)
                }

                override fun onFault(message: String) {
                    Log.i("meishi", message)
                    view.onFault(message)
                }

            })
    }


}