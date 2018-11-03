package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionMainService {



    @POST("home")
    fun homeIndex(): Observable<ResponseBody>// 用户首页



    @FormUrlEncoded
    @POST("shop/recProduct")
    fun recProduct(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 获取推荐商品


}