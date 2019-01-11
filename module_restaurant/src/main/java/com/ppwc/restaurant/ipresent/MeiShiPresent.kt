package com.ppwc.restaurant.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import android.widget.Toast
import com.ppwc.restaurant.apis.MeiShiService
import com.ppwc.restaurant.bean.MeiShiFootBean
import com.ppwc.restaurant.bean.MeiShiHeadBean
import com.ppwc.restaurant.iview.MeiShiHeadView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class MeiShiPresent(var context: Context, var view: MeiShiHeadView) : BasePresent() {




    fun meishi(code:String) {
        map.clear()
        map["code"]= code
        toSubscribe2(ApiServices.getInstance().create(MeiShiService::class.java).meishi(map), object : ProcessObserver2(context,true) {
            override fun processValue(response: String?) {
                Log.i("meishi", response)
                val model = gson.fromJson<MeiShiHeadBean>(response, MeiShiHeadBean::class.java)
                view.MeiShiHeadValue(model)
            }

            override fun onFault(message: String) {
                Log.i("meishi", message)
                view.onFault(message)
            }

        })
    }



}