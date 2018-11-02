package com.yjhh.loginmodule.model

import android.support.v4.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.BaseResponse
import com.yjhh.loginmodule.api.LoginService
import com.yjhh.loginmodule.bean.LoginBean
import io.reactivex.Observable
import okhttp3.ResponseBody

class RegByAccountModel {
    val map = ArrayMap<String, String>()
    fun regByAccount(
        phone: String,
        password: String,
        smsCode: String,
        identity: String,
        refId: String
    ): Observable<BaseResponse<LoginBean>> {
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
        identity: String,
        refId: String
    ): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("phone", phone)
            put("password", password)
            put("smsCode", smsCode)
            put("identity", identity)
            put("refId", refId)

        }

        return ApiServices.getInstance().create(LoginService::class.java).regByAccount2(map)
    }



    /**
     *
     * 获取短信验证码
     * type:1登录 2注册 21 重置密码
     */
    fun sendSms(type: String, phone: String): Observable<BaseResponse<LoginBean>> {
        map.clear()
        with(map) {
            put("type", type)
            put("phone", phone)
        }
        return ApiServices.getInstance().create(LoginService::class.java).sendSms(map)
    }

}