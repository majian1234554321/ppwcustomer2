package com.yjhh.ppwcustomer.model


import android.util.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionUserService
import io.reactivex.Observable
import okhttp3.ResponseBody

class SectionUserModel {

    val map = ArrayMap<String, String>()
    fun setAvater(avaterId: String): Observable<ResponseBody> {
        map.clear()
        map["avaterId"] = avaterId
        return ApiServices.getInstance().create(SectionUserService::class.java).setAvater(map)
    }

    fun getUserinfo(): Observable<ResponseBody> {
        return ApiServices.getInstance().create(SectionUserService::class.java).getUserinfo()
    }


    fun deleteUseraddress(id: String): Observable<ResponseBody> {
        map.clear()
        map["id"] = id
        return ApiServices.getInstance().create(SectionUserService::class.java).deleteUseraddress(map)//删除一个地址
    }


    fun setDefaultUseraddress(id: String): Observable<ResponseBody> {
        map.clear()
        map["id"] = id
        return ApiServices.getInstance().create(SectionUserService::class.java).setDefaultUseraddress(map)//设置为默认的地址
    }

    fun getUseraddressDetail(id: String): Observable<ResponseBody> {
        map.clear()
        map["id"] = id
        return ApiServices.getInstance().create(SectionUserService::class.java).getUseraddressDetail(map)//获取用户所有的地址的信息
    }


    fun getAllUserAddress(isDefault: String?, pageIndex: String, pageSize: String): Observable<ResponseBody> {
        map.clear()
        map["isDefault"] = isDefault
        map["pageIndex"] = pageIndex
        map["pageSize"] = pageSize

        return ApiServices.getInstance().create(SectionUserService::class.java).getAllUserAddress(map)//获取用户存入的所有的地址
    }


    fun setNickName(nickName: String): Observable<ResponseBody> { //设置用户昵称

        map.clear()
        map["nickName"] = nickName
        return ApiServices.getInstance().create(SectionUserService::class.java).setNickName(map)
    }


    fun resetPassword(
        password: String?,
        newPassword: String?,
        smsCode: String?,
        type: String?
    ): Observable<ResponseBody> { //重置用户密码
        map.clear()
        map["password"] = password
        map["newPassword"] = newPassword
        map["smsCode"] = smsCode
        map["type"] = type
        return ApiServices.getInstance().create(SectionUserService::class.java).resetPassword(map)
    }


    fun saveuseraddress(
        id: String //
        , province: String
        , provinceCode: String
        , city: String
        , cityCode: String
        , area: String
        , areaCode: String
        , address: String
        , longitude: String
        , latitude: String
        , userName: String
        , userPhone: String
        , isDef: String
    ): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("id", id) //编辑时必传，新增可空
            put("province", province)//省
            put("provinceCode", provinceCode)//省/代码（静态数据避免频繁联查，这里尽量带上code，方便后面三级联动绑定）
            put("city", city)//市
            put("cityCode", cityCode)//市/代码
            put("area", area)//区
            put("areaCode", areaCode)//区/代码
            put("address", address)//详细地址
            put("longitude", longitude)//经度
            put("latitude", latitude)//纬度
            put("userName", userName)//收货人姓名
            put("userPhone", userPhone)//收货人电话
            put("isDef", isDef)//是否默认
        }
        return ApiServices.getInstance().create(SectionUserService::class.java).saveuseraddress(map)//新增加一个地址
    }


}