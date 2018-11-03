package com.yjhh.ppwcustomer.model

import android.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionOrderService
import io.reactivex.Observable
import okhttp3.ResponseBody

class SectionOrderModel {
    var map = ArrayMap<String, String>()

    fun cancelCause(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).cancelCause(map)
    }

    fun complete(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).complete(map)
    }

    fun confirm(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).confirm(map)
    }

    fun editAddress(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).editAddress(map)
    }

    fun editReceivUser(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).editReceivUser(map)
    }

    fun myOrders(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).myOrders(map)
    }

    fun orderTypes(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).orderTypes(map)
    }

    fun payment(): Observable<ResponseBody> {
        map.clear()

        return ApiServices.getInstance().create(SectionOrderService::class.java).payment(map)
    }

}