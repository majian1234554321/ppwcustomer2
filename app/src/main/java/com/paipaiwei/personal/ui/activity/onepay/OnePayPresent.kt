package com.paipaiwei.personal.ui.activity.onepay

import android.content.Context
import android.util.Log
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class OnePayPresent(var context: Context, var view: OnePayService.OnePayView) : BasePresent() {

    fun oneMoneyBuy(id: String?, number: String?,flag: String) {

        val model = SubmitOneMoneyBuyModel(id, number)

        toSubscribe2(ApiServices.getInstance().create(OnePayService::class.java).oneMoneyBuy(model),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("oneMoneyBuy", response)
                    view.PaiValue(response,flag)
                }

                override fun onFault(message: String) {
                    Log.i("oneMoneyBuy", message)
                    view.onFault(message)
                }

            })
    }


    fun userProp(flag:String) {
        toSubscribe2(ApiServices.getInstance().create(OnePayService::class.java).userProp(),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("userProp", response)
                    view.PaiValue(response,flag)
                }

                override fun onFault(message: String) {
                    Log.i("userProp", message)
                }

            })
    }

}