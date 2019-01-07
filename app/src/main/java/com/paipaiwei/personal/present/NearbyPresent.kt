package com.paipaiwei.personal.present

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.paipaiwei.personal.apis.NearbyService
import com.paipaiwei.personal.bean.NearByDataBean
import com.paipaiwei.personal.bean.NearbyBean
import com.paipaiwei.personal.view.NearbyView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent

class NearbyPresent(var context: Context, var view: NearbyView) : BasePresent() {


    fun nearby() {
        toSubscribe2(
            ApiServices.getInstance().create(NearbyService::class.java).nearby(),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("nearby", response)

                    val model = gson.fromJson<NearbyBean>(response, NearbyBean::class.java)

                    view.onNearby(model)
                }


                override fun onFault(message: String) {
                    Log.i("nearby", message)
                }

            })
    }


    fun nearbyData(categoryId: String, longitude: String, latitude: String, pageIndex: Int, pageSize: Int) {

        map["categoryId"] = categoryId
        map["longitude"] = longitude
        map["latitude"] = latitude
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()



        toSubscribe2(
            ApiServices.getInstance().create(NearbyService::class.java).nearbyData(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("nearbyData", response)

                   val model =  gson.fromJson<NearByDataBean>(response, NearByDataBean::class.java)
                    view.onNearbyData(model)

                }

                override fun onFault(message: String) {
                    Log.i("nearbyData", message)
                    view.onFault(message)
                }

            })
    }

}