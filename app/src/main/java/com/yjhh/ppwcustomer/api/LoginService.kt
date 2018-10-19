package com.yjhh.ppwcustomer.api

import com.yjhh.ppwcustomer.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("path")
    fun login(@FieldMap map: Map<String, String>): Observable<LoginBean>
}