package com.yjhh.ppwcustomer.model

import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.BaseResponse
import com.yjhh.ppwcustomer.interfaces.LoginService

import com.yjhh.ppwcustomer.bean.LoginBean

import io.reactivex.Observable
import okhttp3.ResponseBody

class LoginModel {

    fun login(username: String, password: String, identity: String): Observable<BaseResponse<LoginBean>> {

        val map = androidx.collection.ArrayMap<String, String>()
        with(map) {
            put("account", username)
            put("password", password)
            put("identity", identity)

        }


        return ApiServices.getInstance().create(LoginService::class.java).login(map)
    }



    fun login2(username: String, password: String, identity: String): Observable<ResponseBody> {

        val map = androidx.collection.ArrayMap<String, String>()
        with(map) {
            put("account", username)
            put("password", password)
            put("identity", identity)

        }


        return ApiServices.getInstance().create(LoginService::class.java).login2(map)
    }


}