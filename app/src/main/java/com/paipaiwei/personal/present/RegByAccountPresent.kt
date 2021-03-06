package com.paipaiwei.personal.present

import android.content.Context
import android.util.Log
import com.yjhh.common.api.ProcessObserver
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.paipaiwei.personal.bean.LoginBean

import com.paipaiwei.personal.model.RegByAccountModel
import com.paipaiwei.personal.view.RegistView
import okhttp3.ResponseBody


class RegByAccountPresent(var context: Context, var registView: RegistView) : BasePresent() {

    private val regByAccountModel = RegByAccountModel()





    fun sendSms(type: String, phone: String): Boolean? {

        var send = true

        toSubscribe2(
            regByAccountModel.sendSms(type, phone)
            , object : ProcessObserver2(context) {

                override fun processValue(response: String?) {
                    registView.sendSMSSuccess(response)
                    send = true
                }

                override fun onFault(message: String) {
                    registView.sendSMSFault(message)
                    send = false
                }

            })
        return send
    }



    fun regByAccount2(phone: String, password: String, smsCode: String,  refId: String) {
        toSubscribe2(
            regByAccountModel.regByAccount2(phone, password, smsCode,  refId),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    Log.i("regByAccount2",response)
                    registView.registSuccess2(response)
                }

                override fun onFault(message: String) {
                    Log.i("regByAccount2",message)
                    registView.registFault(message)
                }

            })
    }


}