package com.yjhh.ppwcustomer.model

import android.support.v4.util.ArrayMap
import com.yjhh.ppwcustomer.api.ApiServices
import com.yjhh.ppwcustomer.api.BaseResponse
import com.yjhh.ppwcustomer.api.LoginService
import com.yjhh.ppwcustomer.bean.LoginBean
import io.reactivex.Observable

class LoginModel {

    fun login(username: String, password: String): Observable<LoginBean> {

        val map = ArrayMap<String, String>()
        with(map) {
            put("username", username)
            put("password", password)
        }


        return ApiServices.getInstance().create(LoginService::class.java).login(map)
    }

}