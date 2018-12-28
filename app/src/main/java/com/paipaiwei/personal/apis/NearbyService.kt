package com.paipaiwei.personal.apis

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NearbyService {

    @POST("nearby")
    fun nearby(): Observable<ResponseBody>// 文件上传


    @FormUrlEncoded
    @POST("nearby/data")
    fun nearbyData(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 文件上传
}