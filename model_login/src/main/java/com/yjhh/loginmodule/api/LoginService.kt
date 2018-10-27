package com.yjhh.loginmodule.api

import com.yjhh.common.api.BaseResponse
import com.yjhh.loginmodule.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface LoginService {

    @FormUrlEncoded
    @POST("register/regByAccount")
    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>


    @POST("register/regByAccount")
    fun regByAccount(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>


    @FormUrlEncoded
    @POST("common/sendSms")
    fun sendSms(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>


}