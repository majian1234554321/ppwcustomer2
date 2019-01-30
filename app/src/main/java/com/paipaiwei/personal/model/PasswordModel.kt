package com.paipaiwei.personal.model

import androidx.collection.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionUserService

import com.paipaiwei.personal.interfaces.LoginService
import io.reactivex.Observable
import okhttp3.ResponseBody

class PasswordModel {
    /**
     *
     * 获取短信验证码
     * type:1登录 2注册 21 重置密码
     */

    val map = androidx.collection.ArrayMap<String, String>()

    fun sendSms(type: String, phone: String): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("type", type)
            put("phone", phone)
            put("len", "")

        }
        return ApiServices.getInstance().create(LoginService::class.java).sendSms(map)
    }


    fun fromSms(account: String, smsCode: String, deviceName: String): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("account", account)
            put("smsCode", smsCode)
        }
        return ApiServices.getInstance().create(LoginService::class.java).fromSms(map)
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

        return ApiServices.getInstance().create(LoginService::class.java).regByAccount(map)
    }


    fun forgotPassword(phone: String?, password: String?,smsCode:String?): Observable<ResponseBody> { //

        map.clear()
        map["phone"] = phone
        map["password"] = password
        map["smsCode"] = smsCode
        return ApiServices.getInstance().create(SectionUserService::class.java).forgotPassword(map)
    }
}