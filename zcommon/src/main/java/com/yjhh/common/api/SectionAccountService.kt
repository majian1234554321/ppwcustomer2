package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionAccountService {
    @FormUrlEncoded
    @POST("useraccount/logs")
    fun cards(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录
}