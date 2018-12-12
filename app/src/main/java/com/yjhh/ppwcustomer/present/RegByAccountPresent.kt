package com.yjhh.ppwcustomer.present

import android.content.Context
import com.yjhh.common.api.ProcessObserver
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.yjhh.ppwcustomer.bean.LoginBean

import com.yjhh.ppwcustomer.model.RegByAccountModel
import com.yjhh.ppwcustomer.view.RegistView
import okhttp3.ResponseBody


class RegByAccountPresent(var context: Context, var registView: RegistView) : BasePresent() {

    private val regByAccountModel = RegByAccountModel()






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