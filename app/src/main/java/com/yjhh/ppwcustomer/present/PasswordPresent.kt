package com.yjhh.ppwcustomer.present

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.present.BasePresent

import com.yjhh.ppwbusiness.iview.PasswordView
import com.yjhh.ppwcustomer.model.PasswordModel

class PasswordPresent(var context: Context, var registView: PasswordView) : BasePresent() {

    private val regByAccountModel = PasswordModel()


    fun sendSms(type: String, phone: String) {

        toSubscribe2(
            regByAccountModel.sendSms(type, phone)
            , object : ProcessObserver2(context) {

                override fun processValue(response: String?) {
                    registView.onSuccessSMS(response)
                }

                override fun onFault(message: String) {
                    registView.onFaultSMS(message)
                }

            })
    }

    fun fromSms(account: String, smsCode: String, identity: String, deviceName: String) {


        toSubscribe2(
            regByAccountModel.fromSms(account, smsCode, identity, deviceName)
            , object : ProcessObserver2(context) {

                override fun processValue(response: String?) {
                    registView.onSuccess(response)
                }

                override fun onFault(message: String) {
                    registView.onFault(message)
                }

            })


    }


    fun regByAccount(phone: String, password: String, smsCode: String, identity: String, refId: String) {
        toSubscribe2(
            regByAccountModel.regByAccount2(phone, password, smsCode, identity, refId),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    registView.onSuccess(response)
                }

                override fun onFault(message: String) {
                    registView.onFault(message)
                }

            })
    }


    fun forgotPassword(phone: String?, password: String?, smsCode: String?) {
        toSubscribe2(regByAccountModel.forgotPassword(phone, password, smsCode), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("forgotPassword", response)
                registView.onSuccess(response)

            }

            override fun onFault(message: String) {
                registView.onFault(message)
            }
        })
    }


    fun resetPassword(newPassword: String, smsCode: String,type:String) {
        val map = ArrayMap<String, String>()
        map.put("newPassword", newPassword)
        map.put("smsCode", smsCode)
        map.put("type", type)



        toSubscribe2(
            ApiServices.getInstance().create(SectionUserService::class.java)
            .resetPassword(map),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                registView. onSuccess(response)
            }

            override fun onFault(message: String) {
                registView. onSuccess(message)
            }

        })


    }

}