package com.yjhh.ppwcustomer.present

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.google.gson.Gson
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionCommonService
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.present.BasePresent
import com.yjhh.common.utils.LogUtils

import com.yjhh.ppwcustomer.bean.MyAddressBean
import com.yjhh.ppwcustomer.bean.UserinfoBean
import com.yjhh.ppwcustomer.model.SectionUserModel
import com.yjhh.ppwcustomer.view.MyAddressView
import com.yjhh.ppwcustomer.view.RegistView
import com.yjhh.ppwcustomer.view.UserInfoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.File


class SectionUserPresent(context: Context) : BasePresent() {

    private lateinit var registView: RegistView

    private lateinit var addressView: MyAddressView

    private lateinit var userInfoView: UserInfoView

    constructor(context: Context, userInfoView: UserInfoView) : this(context) {
        this.userInfoView = userInfoView
    }

    constructor(context: Context, registView: RegistView) : this(context) {
        this.registView = registView
    }


    constructor(context: Context, addressView: MyAddressView) : this(context) {
        this.addressView = addressView
    }

    val model = SectionUserModel()

    fun setAvatarUpLoadJoin(file: File): Disposable {

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("multipartFile", file.name, requestFile)


        return ApiServices.getInstance().create(SectionCommonService::class.java)
            .uploadFile(body)
            .flatMap {
                val response = it.string()
                val value = JSONObject(response)
                val map = ArrayMap<String, String>()
                if (value.getBoolean("success")) {

                    val jsonObject = value.getString("data")
                    val jsonObject1 = JSONArray(jsonObject)
                    val js = jsonObject1.get(0) as JSONObject
                    val id = js.getString("id")
                    map["avatarId"] = id
                }
                ApiServices.getInstance().create(SectionUserService::class.java)
                    .setAvatar(map)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                LogUtils.i("UploadActivity", it.string())
            }, {
                LogUtils.i("UploadActivity", it.toString())
            }
            )

    }

    fun forgotPassword(phone: String?, password: String?, smsCode: String?) {
        toSubscribe2(model.forgotPassword(phone, password, smsCode), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("forgotPassword", response)
                registView.registSuccess2(response)

            }

            override fun onFault(message: String) {
                registView.registFault(message)
            }
        })
    }


    fun resetPassword(
        password: String?,
        newPassword: String?,
        smsCode: String?,
        type: String?
    ) {
        toSubscribe2(model.resetPassword(password, newPassword, smsCode, type), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("resetPassword", response)
                registView.registSuccess2(response)
            }

            override fun onFault(message: String) {
                registView.registFault(message)
            }
        })
    }

    fun setNickName(nickName: String) {
        toSubscribe2(model.setNickName(nickName), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("setNickName", response)
                userInfoView.onSuccess(UserinfoBean())

            }

            override fun onFault(message: String) {
                userInfoView.onFault(message)
            }
        })
    }


    fun setMobile(mobile: String, phone: String, smsCode: String) {
        toSubscribe2(model.setMobile(mobile, phone, smsCode), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("setNickName", response)
                registView.registSuccess2(response)

            }

            override fun onFault(message: String) {
                registView.registFault(message)
            }
        })
    }


    fun setBirthday(birthday: String) {
        toSubscribe2(model.setBirthday(birthday), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("setNickName", response)

            }

            override fun onFault(message: String) {

            }
        })
    }


    fun getUserinfo() {
        toSubscribe2(model.getUserinfo(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("getUserinfo", response)

                val bean = gson.fromJson<UserinfoBean>(response, UserinfoBean::class.java)

                userInfoView.onSuccess(bean)
            }

            override fun onFault(message: String) {
                userInfoView.onFault(message)
            }
        })

    }

    fun deleteUseraddress(id: String?) {
        toSubscribe2(model.deleteUseraddress(id), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("deleteUseraddress", response)
                addressView.onSuccess(MyAddressBean(), "")

            }

            override fun onFault(message: String) {
                addressView.onFault(message)
            }

        })
    }

    fun setDefaultUseraddress(id: String) {

        toSubscribe2(model.setDefaultUseraddress(id), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("deleteUseraddress", response)

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun getUseraddressDetail(id: String) {


        toSubscribe2(model.getUseraddressDetail(id), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("getUseraddressDetail", response)

            }

            override fun onFault(message: String) {

            }

        })

    }

    fun getAllUserAddress(isDefault: String?, pageIndex: Int, pageSize: Int, flag: String) {
        toSubscribe2(model.getAllUserAddress(isDefault, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("getAllUserAddress", response)

                val bean = gson.fromJson<MyAddressBean>(response, MyAddressBean::class.java)

                addressView.onSuccess(bean, flag)

            }

            override fun onFault(message: String) {

            }

        })
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
    ) {
        toSubscribe2(model.editByLocation(
            id,
            gender,
            name,
            phone,
            address,
            no,
            tags,
            longitude,
            latitude,
            def

        ), object : ProcessObserver2(context) {
            override fun onFault(message: String) {
                addressView.onFault(message)
            }

            override fun processValue(response: String?) {
                Log.i("editByLocation", response)
                addressView.onSuccess(MyAddressBean(), "flag")
            }

        })


    }


}