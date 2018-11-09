package com.yjhh.common.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionMembershipService {

    @FormUrlEncoded
    @POST("membershipcard")
    fun myMembershipcard(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("membershipcard/buyConfirm")
    fun buyConfirm(@FieldMap map: Map<String, String>): Observable<ResponseBody>//



    @POST("membershipcard/cards")
    fun membershipcardcards(): Observable<ResponseBody>//



    @POST("membershipcard/types")
    fun membershipcardtypes(): Observable<ResponseBody>//


}
