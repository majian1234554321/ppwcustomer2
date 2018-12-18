package com.paipaiwei.personal.model

import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.BaseResponse
import com.paipaiwei.personal.interfaces.LoginService

import com.paipaiwei.personal.bean.LoginBean

import io.reactivex.Observable
import okhttp3.ResponseBody

class LoginModel {

    fun login(username: String, password: String): Observable<BaseResponse<LoginBean>> {

        val map = androidx.collection.ArrayMap<String, String>()
        with(map) {
            put("account", username)
            put("password", password)

        }


        return ApiServices.getInstance().create(LoginService::class.java).login(map)
    }



    fun login2(username: String, password: String): Observable<ResponseBody> {

        val map = androidx.collection.ArrayMap<String, String>()
        with(map) {
            put("account", username)
            put("password", password)


        }


        return ApiServices.getInstance().create(LoginService::class.java).login2(map)
    }


}