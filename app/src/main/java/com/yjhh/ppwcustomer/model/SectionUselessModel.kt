package com.yjhh.ppwcustomer.model

import android.support.v4.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionCouponService
import com.yjhh.common.api.SectionMainService
import com.yjhh.common.api.SectionUselessService
import io.reactivex.Observable
import okhttp3.ResponseBody

class SectionUselessModel {

    val map = ArrayMap<String, String>()


    fun userhistory(type: String, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["type"] = type
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        return ApiServices.getInstance().create(SectionUselessService::class.java).userhistory(map)
    }
}