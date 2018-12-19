package com.paipaiwei.personal.present

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.paipaiwei.personal.apis.SignService
import com.paipaiwei.personal.bean.SignBean
import com.paipaiwei.personal.bean.SignResultBean
import com.paipaiwei.personal.view.SignView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent


class SignPresent(var context: Context, var view: SignView) : BasePresent() {


    val ob = ApiServices.getInstance().create(SignService::class.java)

    fun userSignInList() {
        toSubscribe2(ob.userSignInList(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("userSignInList", response)


               val modle =  gson.fromJson<SignBean>(response,SignBean::class.java)


                view.onSuccessList(modle)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        })
    }


    fun sign() {
        toSubscribe2(ob.sign(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("userSignInList", response)

               val model =  gson.fromJson<SignResultBean>(response,SignResultBean::class.java)
                view.onSuccessSign(model)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        })
    }


}