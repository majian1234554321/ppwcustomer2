package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionOrderService {


    @FormUrlEncoded
    @POST("order/cancelCause")
    fun cancelCause(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/complete")
    fun complete(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/confirm")
    fun confirm(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/editAddress")
    fun editAddress(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/editReceivUser")
    fun editReceivUser(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/myOrders")
    fun myOrders(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/orderTypes")
    fun orderTypes(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("order/payment")
    fun payment(@FieldMap map: Map<String, String>): Observable<ResponseBody>//





}