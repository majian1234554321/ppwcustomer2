package com.ppwc.restaurant.iview

import com.yjhh.common.base.BaseView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RestaurantOrderSerVice {

    @FormUrlEncoded
    @POST("order/detail")
    fun detail(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/del")
    fun del(@FieldMap map: Map<String, String>): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("userAccount/logs")
    fun logs(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    interface RestaurantOrderView : BaseView {
        fun onRestaurantOrder(model: String?,flag:String)
    }



}