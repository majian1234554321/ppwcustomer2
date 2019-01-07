package com.paipaiwei.personal.ui.activity.pai

import com.yjhh.common.base.BaseView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PaiService {
    @FormUrlEncoded
    @POST("qiangPai")
    fun qiangPai(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录

    interface PaiView : BaseView {
        fun PaiValue(model: String?, flag: String)
    }

}