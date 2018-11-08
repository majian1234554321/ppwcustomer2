package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionCouponService {

    @FormUrlEncoded
    @POST("coupon")
    fun coupon(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 获取优惠券 状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
}