package com.paipaiwei.personal.apis

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SearchInfoService {


    @POST("home/searchHot")
    fun searchHot(): Observable<ResponseBody>// 资金记录/积分记录
}