package com.ppwc.restaurant.views.reserve

import android.content.Context
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class ReservePresent(var context: Context, var view: ReserveService.ReserveView) : BasePresent() {


    fun userReservation(status: String, pageIndex: Int, pageSize: Int, flag: String) {

        map.clear()
        map.put("status", status)
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())




        toSubscribe2(ApiServices.getInstance().create(ReserveService::class.java).userReservation(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.reserveSuccess(response, flag)
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })
    }
}