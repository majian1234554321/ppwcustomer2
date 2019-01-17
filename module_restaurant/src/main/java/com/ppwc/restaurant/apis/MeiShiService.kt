package com.ppwc.restaurant.apis

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface MeiShiService {


    @Multipart
    @POST("common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传


    @FormUrlEncoded
    @POST("home/moduleData")
    fun meishiData(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录

    @FormUrlEncoded
    @POST("home/module")
    fun module(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录




}