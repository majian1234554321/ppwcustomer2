package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionUselessService {

    @FormUrlEncoded
    @POST("userhistory")
    fun userhistory(@FieldMap map: Map<String, String>): Observable<ResponseBody>//
}
