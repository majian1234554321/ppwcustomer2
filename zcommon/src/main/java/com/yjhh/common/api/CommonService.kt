package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface CommonService {


    @Multipart
    @POST("common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传


    @Multipart
    @POST("common/upload")
    fun uploadFiles(@Part() list: List<MultipartBody.Part>): Observable<ResponseBody>// 文件上传


    @POST("common/version")
    fun version(): Observable<ResponseBody>// 文件上传

    @POST("common/init")
    fun init(): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("usercollect/collect")
    fun collect(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("product/zan")
    fun zan(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

}