package com.yjhh.ppwcustomer.interfaces

import com.yjhh.common.api.BaseResponse
import com.yjhh.ppwcustomer.bean.LoginBean

import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface LoginService {

    @FormUrlEncoded
    @POST("login/fromAccount")
    fun login(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>

    @FormUrlEncoded
    @POST("register/regByAccount")
    fun regByAccount(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>


    @FormUrlEncoded
    @POST("common/sendSms")
    fun sendSms(@FieldMap map: Map<String, String>): Observable<BaseResponse<LoginBean>>


    @FormUrlEncoded
    @POST("login/fromAccount")
    fun login2(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("register/regByAccount")
    fun regByAccount2(@FieldMap map: Map<String, String>): Observable<ResponseBody>


}