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
    @POST("useraccount/logs")
    fun useraccount(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    @POST("useraccount")
    fun useraccountindex(): Observable<ResponseBody>// 我的余额/积分


    @POST("about")
    fun about(): Observable<ResponseBody>// 我的余额/积分


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("about/feedback")
    fun feedback(@Body map: SubmitFeedbackModel): Observable<ResponseBody>

    @POST("shopAdmin/curr")
    fun currInfo(): Observable<ResponseBody>


}
