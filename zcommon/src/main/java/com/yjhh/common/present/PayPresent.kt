package com.yjhh.common.present

import android.content.Context
import android.util.ArrayMap
import android.util.Log

import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.PayService
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.iview.PayView
import com.yjhh.common.present.BasePresent

class PayPresent(var context: Context,var payViiew:PayView) : BasePresent() {



    fun paymentByWx(id:String,money:String) {

        map.clear()
        map["id"] = id
        map["money"] = money
        toSubscribe2(ApiServices.getInstance().create(PayService::class.java).paymentByWx(map),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                Log.i("paymentByWx",response)
                payViiew.onWxPayValue(response)
            }

            override fun onFault(message: String) {
                Log.i("paymentByWx",message)
            }

        })

    }




    fun paymentByAli(id:String,money:String) {

        map.clear()
        map["id"] = id
        map["money"] = money
        toSubscribe2(ApiServices.getInstance().create(PayService::class.java).paymentByAli(map),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                Log.i("paymentByAli",response)
                payViiew.onAliPayValue()
            }

            override fun onFault(message: String) {
                Log.i("paymentByAli",message)
            }

        })

    }


}