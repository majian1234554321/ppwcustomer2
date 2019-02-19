package com.ppwc.restaurant.views.reserve

import com.yjhh.common.base.BaseView
import com.yjhh.common.bean.SubmitReservationModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface ReserveService {

    @POST("userReservation/cancelCause")
    fun cancelCause(): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("userReservation")
    fun userReservation(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("userReservation/cancel")
    fun cancelReservation(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("userReservation/detail")
    fun detailReservation(@FieldMap map: Map<String, String>): Observable<ResponseBody>








    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("userReservation/submit")
    fun submitReservation(@Body map: SubmitReservationModel): Observable<ResponseBody>

    interface ReserveView:BaseView {
        fun reserveSuccess(jsonValue:String?,flag:String?)

    }

}