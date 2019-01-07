package com.paipaiwei.personal.present

import android.content.Context
import android.util.Log
import androidx.collection.ArrayMap
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.paipaiwei.personal.apis.AboutService
import com.paipaiwei.personal.apis.OrderService

import com.paipaiwei.personal.view.AboutView
import com.paipaiwei.personal.view.OrderView

class OrderPresent(var context: Context, var view: OrderView) : BasePresent() {

    val obs = ApiServices.getInstance().create(OrderService::class.java)

    fun orderTypes() {
        toSubscribe2(
            obs.orderTypes(), object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.onSuccessOrder(response, "")

                    Log.i("orderTypes", response)

                }

                override fun onFault(message: String) {

                }

            }
        )

    }


    fun payment(id: String?, money: String?) {
        map.clear()
        map.put("id", id)
        map.put("money", money)
        toSubscribe2(obs
            .payment(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }


    fun complete(id: String?, money: String?) {
        map.clear()
        map.put("id", id)
        map.put("money", money)
        toSubscribe2(obs
            .complete(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }

    fun cancelCause(id: String?, money: String?) {
        map.clear()
        map.put("id", id)
        map.put("money", money)
        toSubscribe2(obs
            .cancelCause(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }

    fun confirm(id: String?, money: String?) {
        map.clear()
        map.put("id", id)
        map.put("money", money)
        toSubscribe2(obs
            .confirm(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }

    fun editAddress(id: String?, money: String?) {
        map.clear()
        map.put("id", id)
        map.put("money", money)
        toSubscribe2(obs
            .editAddress(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }

    fun editReceivUser(id: String?, money: String?) {
        map.clear()
        map.put("id", id)
        map.put("money", money)
        toSubscribe2(obs
            .editReceivUser(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }

    fun myOrders(type: String?, status: String?, pageIndex: Int?, pageSize: Int?, flag: String?) {
        map.clear()

        map.put("type", type)
        map.put("status", status)
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())
        toSubscribe2(obs
            .myOrders(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccessOrder(response, flag)
                Log.i("myOrders", "Main2_1Fragment")
            }

            override fun onFault(message: String) {
                view.onFault(message)
                Log.i("myOrders", "Main2_1Fragment")
            }

        }
        )

    }


    fun detailFromCallback(id: String, type: String) {
        map.clear()

        map["id"] = id
        map["type"] = type //1微信 2支付宝 4银联

        toSubscribe2(ApiServices.getInstance().create(OrderService::class.java).detailFromCallback(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    Log.i("detailFromCallback", response)


                    view.onSuccessOrder(response, "detailFromCallback")
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })

    }


}