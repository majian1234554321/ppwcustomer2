package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface QueryService {


    @Multipart
    @POST("common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传


    @FormUrlEncoded
    @POST("home/searchResult")
    fun searchResult(@FieldMap map: Map<String, String>): Observable<ResponseBody>

    @FormUrlEncoded
    @POST("home/module")
    fun meishi(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录




}