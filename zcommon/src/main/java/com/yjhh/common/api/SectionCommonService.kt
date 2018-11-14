package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface SectionCommonService {

    @Multipart
    @POST("common/upload")
    fun uploadFile( @Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传
}