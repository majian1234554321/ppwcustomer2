package com.paipaiwei.personal.apis

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*
import com.paipaiwei.personal.bean.SubmitFeedbackModel

interface SectionUselessService {

    @FormUrlEncoded
    @POST("userhistory")
    fun userhistory(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("usermessage")
    fun usermessage(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("usercollect")
    fun usercollect(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 我的收藏


    @FormUrlEncoded
    @POST("userAccount/logs")
    fun useraccount(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    @POST("userAccount")
    fun useraccountindex(): Observable<ResponseBody>// 我的余额/积分


    @POST("about")
    fun about(): Observable<ResponseBody>// 我的余额/积分


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("about/feedback")
    fun feedback(@Body map: SubmitFeedbackModel): Observable<ResponseBody>

    @POST("shopAdmin/curr")
    fun currInfo(): Observable<ResponseBody>


    @POST("userSignIn")
    fun userSignIn(): Observable<ResponseBody> //签到列表

    @POST("userSignIn/sign")
    fun signList(): Observable<ResponseBody> //签到列表

    @FormUrlEncoded
    @POST("userreservation")
    fun userreservation(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 我的预订列表

    @FormUrlEncoded
    @POST("userreservation/cancel")
    fun cancel(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 取消

    @FormUrlEncoded
    @POST("userreservation/cancelCause")
    fun cancelCause(@FieldMap map: Map<String, String>): Observable<ResponseBody>// userreservation/cancelCause

    @FormUrlEncoded
    @POST("userreservation/detail")
    fun detail(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录







}
