package com.paipaiwei.personal.apis

import com.yjhh.common.base.BaseView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QiangPaiService {


    @FormUrlEncoded
    @POST("qiangPai")
    fun qiangPaiList(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    @FormUrlEncoded
    @POST("qiangPai/detail")
    fun detail(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录

    @FormUrlEncoded
    @POST("qiangPai/pai")
    fun qiangPai(@FieldMap map: Map<String, String>): Observable<ResponseBody>



    @FormUrlEncoded
    @POST("qiangPai/buy")
    fun qiangPaiBuy(@FieldMap map: Map<String, String>): Observable<ResponseBody>




    interface QiangPaiView : BaseView {
        fun qiangPaiValue(response: String?, flag: String?)
    }

}