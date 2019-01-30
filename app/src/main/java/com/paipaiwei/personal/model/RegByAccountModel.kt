package com.paipaiwei.personal.model

import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.BaseResponse
import com.paipaiwei.personal.interfaces.LoginService

import com.paipaiwei.personal.bean.LoginBean
import io.reactivex.Observable
import okhttp3.ResponseBody

class RegByAccountModel {
    val map = androidx.collection.ArrayMap<String, String>()
    fun regByAccount(
        phone: String,
        password: String,
        smsCode: String,

        refId: String
    ): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("phone", phone)
            put("password", password)
            put("smsCode", smsCode)
            put("refId", refId)


        }

        return ApiServices.getInstance().create(LoginService::class.java).regByAccount(map)
    }


    fun regByAccount2(
        phone: String,
        password: String,
        smsCode: String,

        refId: String
    ): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("phone", phone)
            put("password", password)
            put("smsCode", smsCode)

            put("refId", refId)

        }

        return ApiServices.getInstance().create(LoginService::class.java).regByAccount2(map)
    }


    /**
     *
     * 获取短信验证码
     * type:1登录 2注册 21 重置密码
     */
    fun sendSms(type: String, phone: String): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("type", type)
            put("phone", phone)
            put ("len", "6")

        }
        return ApiServices.getInstance().create(LoginService::class.java).sendSms(map)
    }

}