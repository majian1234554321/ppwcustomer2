package com.yjhh.ppwcustomer.present

import android.content.Context
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.yjhh.ppwcustomer.model.SectionMainModel
import com.yjhh.ppwcustomer.model.SectionOrderModel

class SectionOrderPresent(context: Context) : BasePresent() {

    val model = SectionOrderModel()

    fun cancelCause() {
        toSubscribe2(model.cancelCause(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }


    fun complete() {
        toSubscribe2(model.complete(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun confirm() {
        toSubscribe2(model.confirm(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun editAddress() {
        toSubscribe2(model.editAddress(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun editReceivUser() {
        toSubscribe2(model.editReceivUser(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun myOrders() {
        toSubscribe2(model.myOrders(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun orderTypes() {
        toSubscribe2(model.orderTypes(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun payment() {
        toSubscribe2(model.payment(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFault(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}