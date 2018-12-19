package com.paipaiwei.personal.apis

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface SignService {






    @POST("userSignIn")
    fun userSignInList(): Observable<ResponseBody>// 资金记录/积分记录



    @POST("userSignIn/sign")
    fun sign(): Observable<ResponseBody>// 资金记录/积分记录


}