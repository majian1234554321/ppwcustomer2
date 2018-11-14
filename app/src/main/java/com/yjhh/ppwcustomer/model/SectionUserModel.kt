package com.yjhh.ppwcustomer.model


import android.util.ArrayMap
import com.yjhh.common.Constants.province
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


    fun setBirthday(birthday:String): Observable<ResponseBody>{
        map.clear()
        map["birthday"] = birthday
        return ApiServices.getInstance().create(SectionUserService::class.java).setBirthday(map)
    }




    fun setMobile(mobile:String,phone:String,smsCode:String): Observable<ResponseBody>{
        map.clear()
        map["mobile"] = mobile
        map["phone"] = phone
        map["smsCode"] = smsCode

        return ApiServices.getInstance().create(SectionUserService::class.java).setMobile(map)
    }



    fun getUserinfo(): Observable<ResponseBody> {
        return ApiServices.getInstance().create(SectionUserService::class.java).getUserinfo()
    }


    fun deleteUseraddress(id: String?): Observable<ResponseBody> {
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


    fun getAllUserAddress(isDefault: String?, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["isDefault"] = isDefault
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()

        return ApiServices.getInstance().create(SectionUserService::class.java).getAllUserAddress(map)//获取用户存入的所有的地址
    }


    fun setNickName(nickName: String): Observable<ResponseBody> { //设置用户昵称

        map.clear()
        map["nickName"] = nickName
        return ApiServices.getInstance().create(SectionUserService::class.java).setNickName(map)
    }



    fun forgotPassword(phone: String?, password: String?,smsCode:String?): Observable<ResponseBody> { //

        map.clear()
        map["phone"] = phone
        map["password"] = password
        map["smsCode"] = smsCode
        return ApiServices.getInstance().create(SectionUserService::class.java).forgotPassword(map)
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


    fun editByLocation(
        id: String //
        , gender: String
        , name: String
        , phone: String
        , address: String
        , no: String
        , tags: String
        , longitude: String
        , latitude: String
        , def: String

    ): Observable<ResponseBody> {
        map.clear()
        with(map) {
            put("id", id) //
            put("name", name)//省
            put("phone", phone)
            put("gender", gender)//市
            put("no", no)
            put("tags", tags)//区

            put("address", address)
            put("longitude", longitude)
            put("latitude", latitude)
            put("def", def)

        }
        return ApiServices.getInstance().create(SectionUserService::class.java).editByLocation(map)//新增加一个地址
    }


}