package com.paipaiwei.personal.present

import android.content.Context
import com.yjhh.common.BaseApplication.context
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.paipaiwei.personal.model.SectionMainModel
import com.paipaiwei.personal.model.SectionOrderModel

class SectionOrderPresent(context: Context) : BasePresent() {

    val model = SectionOrderModel()

    fun cancelCause() {
        toSubscribe2(model.cancelCause(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }


    fun complete() {
        toSubscribe2(model.complete(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun confirm() {
        toSubscribe2(model.confirm(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun editAddress() {
        toSubscribe2(model.editAddress(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun editReceivUser() {
        toSubscribe2(model.editReceivUser(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun myOrders() {
        toSubscribe2(model.myOrders(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun orderTypes() {
        toSubscribe2(model.orderTypes(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

    fun payment() {
        toSubscribe2(model.payment(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

            }

            override fun onFault(message: String) {

            }

        })
    }

}