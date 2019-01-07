package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PayService  {

    @FormUrlEncoded
    @POST("order/paymentByWx")
    fun paymentByWx(@FieldMap map: Map<String, String>): Observable<ResponseBody>//商家首页



    @FormUrlEncoded
    @POST("order/paymentByAli")
    fun paymentByAli(@FieldMap map: Map<String, String>): Observable<ResponseBody>//商家首页

}