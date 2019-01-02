package com.paipaiwei.personal.apis

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MembershipCardService {

    @FormUrlEncoded
    @POST("coupon")
    fun coupon(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录
}