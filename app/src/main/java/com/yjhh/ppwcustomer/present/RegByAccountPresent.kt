package com.yjhh.ppwcustomer.present

import android.content.Context
import com.yjhh.common.api.ProcessObserver
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.yjhh.ppwcustomer.bean.LoginBean

import com.yjhh.ppwcustomer.model.RegByAccountModel
import com.yjhh.ppwcustomer.view.RegistView


class RegByAccountPresent(var context: Context, var registView: RegistView) : BasePresent() {

    private val regByAccountModel = RegByAccountModel()


    fun sendSms(type: String, phone: String) {

        toSubscribe(regByAccountModel.sendSms(type, phone), object : ProcessObserver<LoginBean>(context) {


            override fun onSuccess(data: LoginBean?) {
                registView.sendSMSSuccess(data)
            }

            override fun onFault(message: String) {
                registView.sendSMSFault(message)
            }

        })
    }


    fun regByAccount(phone: String, password: String, smsCode: String, identity: String, refId: String) {
        toSubscribe(
            regByAccountModel.regByAccount(phone, password, smsCode, identity, refId),
            object : ProcessObserver<LoginBean>(context) {
                override fun onSuccess(data: LoginBean?) {
                    registView.registSuccess(data)
                }

                override fun onFault(message: String) {
                    registView.registFault(message)
                }

            })
    }


    fun regByAccount2(phone: String, password: String, smsCode: String, identity: String, refId: String) {
        toSubscribe2(
            regByAccountModel.regByAccount2(phone, password, smsCode, identity, refId),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    registView.registSuccess2(response)
                }

                override fun onFault(message: String) {
                    registView.registFault(message)
                }

            })
    }


}