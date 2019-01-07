package com.paipaiwei.personal.ui.activity.onepay

import com.yjhh.common.base.BaseView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OnePayService {



    @POST("userProp/oneMoneyBuy")
    fun oneMoneyBuy(@Body model: SubmitOneMoneyBuyModel?): Observable<ResponseBody>//



    @POST("userProp")
    fun userProp(): Observable<ResponseBody>//


    interface OnePayView : BaseView {
        fun PaiValue(model: String?, flag: String)
    }


}