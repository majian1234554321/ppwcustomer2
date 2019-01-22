package com.paipaiwei.personal.ui.fragment.czqg

import com.yjhh.common.base.BaseView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BuyOvervalueService {
    @FormUrlEncoded
    @POST("qiangPai/supData")
    fun overByList(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @POST("qiangPai/sup")
    fun overBynav(): Observable<ResponseBody>






    interface BuyOvervalueView : BaseView {
        fun onBuyOvervalue(response: String?, flag: String?)
    }

}
