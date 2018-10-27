package com.yjhh.loginmodule.present

import android.content.Context
import com.yjhh.common.api.ProcessObserver
import com.yjhh.common.present.BasePresent
import com.yjhh.loginmodule.bean.LoginBean
import com.yjhh.loginmodule.model.RegByAccountModel
import com.yjhh.loginmodule.view.RegistView

class RegByAccountPresent(var context: Context, var registView: RegistView) : BasePresent() {

    private val regByAccountModel = RegByAccountModel()


    fun sendSms(type: String, phone: String) {

        toSubscribe(regByAccountModel.sendSms(type, phone), object : ProcessObserver<LoginBean>(context) {
            override fun onSuccess(data: LoginBean) {
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
                override fun onSuccess(data: LoginBean) {
                    registView.registSuccess(data)
                }

                override fun onFault(message: String) {
                    registView.registFault(message)
                }

            })
    }
}