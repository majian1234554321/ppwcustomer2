package com.yjhh.loginmodule.model

import android.support.v4.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.loginmodule.api.LoginService
import com.yjhh.loginmodule.bean.LoginBean

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