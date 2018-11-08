package com.yjhh.ppwcustomer.model

import android.support.v4.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionCouponService
import com.yjhh.common.api.SectionMainService
import io.reactivex.Observable
import okhttp3.ResponseBody

class SectionCouponModel {

    val map = ArrayMap<String, String>()




    fun coupon(status:String ,pageIndex:Int,pageSize:Int): Observable<ResponseBody> {
        map.clear()



        map["status"] = status
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        return  ApiServices.getInstance().create(SectionCouponService::class.java).coupon(map)
    }
}