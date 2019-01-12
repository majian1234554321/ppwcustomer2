package com.ppwc.restaurant.iview

import com.ppwc.restaurant.bean.ShopPayPageInitModel
import com.ppwc.restaurant.bean.SubmitShopPayModel
import com.yjhh.common.base.BaseView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PrePayCheckServie {


    @POST("shop/pay")
    fun pay(@Body map: ShopPayPageInitModel): Observable<ResponseBody>//买单界面初始化


    @POST("shop/paySubmit")
    fun paySubmit(@Body map: SubmitShopPayModel): Observable<ResponseBody>//买单数据提交


    interface PrePayCheckView : BaseView {
        fun onPrePayCheck(model: String?)
    }
}