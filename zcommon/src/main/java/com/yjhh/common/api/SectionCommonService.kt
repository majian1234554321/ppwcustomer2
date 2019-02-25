package com.yjhh.common.api

import com.yjhh.common.bean.ShareModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface SectionCommonService {

    @Multipart
    @POST("common/upload")
    fun uploadFile( @Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传




    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("common/share")
    fun share(@Body map: ShareModel): Observable<ResponseBody>



}