package com.paipaiwei.personal.apis


import com.paipaiwei.personal.bean.SubmitUserCommentModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface SectionEvluateService {


    @POST("shopAdmin")
    fun shopAdmin(): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("userComment")
    fun allcomments(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("userComment/detail")
    fun comment(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("userComment/reply")
    fun reply(@Body map: SubmitUserCommentModel): Observable<ResponseBody>//



    @POST("shopAdminComment/nav")
    fun nav(): Observable<ResponseBody>//
}
