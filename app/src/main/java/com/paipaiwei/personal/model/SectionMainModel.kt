package com.paipaiwei.personal.model

import android.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionMainService
import io.reactivex.Observable
import okhttp3.ResponseBody

import java.lang.reflect.Array

class SectionMainModel {

    val map = ArrayMap<String, String>()

    fun homeIndex() = ApiServices.getInstance().create(SectionMainService::class.java).homeIndex()


    fun recProduct( pageIndex:Int,pageSize:Int): Observable<ResponseBody>{
        map.clear()
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
      return  ApiServices.getInstance().create(SectionMainService::class.java).recProduct(map)
    }


}
