package com.ppwc.restaurant.ipresent

import android.content.Context
import android.util.Log
import com.ppwc.restaurant.iview.RestaurantOrderSerVice
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseView
import com.yjhh.common.present.BasePresent

class RestaurantOrderPresent(var context: Context, var view: RestaurantOrderSerVice.RestaurantOrderView) :
    BasePresent() {

    fun detail(orderId: String?,flag:String) {
        map.clear()
        map.put("id", orderId)

        toSubscribe2(ApiServices.getInstance().create(RestaurantOrderSerVice::class.java).detail(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("detail", response)
                    view.onRestaurantOrder(response,flag)
                }

                override fun onFault(message: String) {
                    Log.i("detail", message)
                }

            })
    }


    fun del(orderId: String?,flag:String) {
        map.clear()
        map.put("id", orderId)

        toSubscribe2(ApiServices.getInstance().create(RestaurantOrderSerVice::class.java).del(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("detail", response)
                    view.onRestaurantOrder(response,flag)
                }

                override fun onFault(message: String) {
                    Log.i("detail", message)
                }

            })
    }




    fun logs(type: String?,pageIndex:Int,pageSize:Int, flag:String) {
        map.clear()
        map.put("type", type)
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())

        toSubscribe2(ApiServices.getInstance().create(RestaurantOrderSerVice::class.java).logs(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("logs", response)
                    view.onRestaurantOrder(response,flag)
                }

                override fun onFault(message: String) {
                    Log.i("logs", message)
                }

            })
    }



}