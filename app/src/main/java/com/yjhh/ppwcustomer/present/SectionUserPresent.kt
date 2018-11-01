package com.yjhh.ppwcustomer.present

import android.content.Context
import android.util.Log
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseApplication.context
import com.yjhh.common.present.BasePresent
import com.yjhh.ppwcustomer.model.SectionUserModel


class SectionUserPresent(context: Context) : BasePresent() {

    val model = SectionUserModel()

    fun setAvater() {

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

            }

            override fun onFault(message: String) {

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

            }

            override fun onFault(message: String) {

            }
        })

    }

    fun deleteUseraddress(id: String) {
        toSubscribe2(model.getUseraddressDetail(id), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("deleteUseraddress", response)

            }

            override fun onFault(message: String) {

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

    fun getAllUserAddress(isDefault: String?, pageIndex: String, pageSize: String) {
        toSubscribe2(model.getAllUserAddress(isDefault, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("getAllUserAddress", response)


            }

            override fun onFault(message: String) {

            }

        })
    }

    fun saveuseraddress() {


    }


}