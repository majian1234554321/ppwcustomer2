package com.yjhh.ppwcustomer.present

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.present.BasePresent
import com.yjhh.loginmodule.view.RegistView
import com.yjhh.ppwcustomer.bean.MyAddressBean
import com.yjhh.ppwcustomer.bean.UserinfoBean
import com.yjhh.ppwcustomer.model.SectionUserModel
import com.yjhh.ppwcustomer.view.MyAddressView
import com.yjhh.ppwcustomer.view.UserInfoView


class SectionUserPresent(context: Context) : BasePresent() {

    private lateinit var registView: RegistView

    private lateinit var addressView: MyAddressView

    private lateinit var  userInfoView: UserInfoView

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

    fun setAvater() {

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

            }

            override fun onFault(message: String) {

            }
        })
    }

    fun getUserinfo() {
        toSubscribe2(model.getUserinfo(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("getUserinfo", response)

                val  bean = gson.fromJson<UserinfoBean>(response,UserinfoBean::class.java)

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