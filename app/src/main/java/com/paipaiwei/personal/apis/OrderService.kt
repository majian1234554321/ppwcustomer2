package com.paipaiwei.personal.apis

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface OrderService {


    @FormUrlEncoded
    @POST("order/complete")
    fun complete(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 文件上传


    @FormUrlEncoded
    @POST("about/feedbackList")
    fun cancelCause(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录




    @FormUrlEncoded
    @POST("order/confirm")
    fun confirm(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录

    @FormUrlEncoded
    @POST("order/editAddress")
    fun editAddress(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    @FormUrlEncoded
    @POST("order/editReceivUser")
    fun editReceivUser(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    @FormUrlEncoded
    @POST("order")
    fun myOrders(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("order/payment")
    fun payment(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("order/detailFromCallback")
    fun detailFromCallback(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @POST("order/nav")
    fun nav(): Observable<ResponseBody>//


}