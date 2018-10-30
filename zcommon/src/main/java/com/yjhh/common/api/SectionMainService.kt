package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionMainService {


    @FormUrlEncoded
    @POST("home/index")
    fun cards(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 用户首页


}