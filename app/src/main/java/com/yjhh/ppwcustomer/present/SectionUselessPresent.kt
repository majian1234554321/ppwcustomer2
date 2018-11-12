package com.yjhh.ppwcustomer.present

import android.content.Context
import android.util.Log
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.bean.MyMessageBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean
import com.yjhh.ppwcustomer.model.SectionCouponModel
import com.yjhh.ppwcustomer.model.SectionUselessModel
import com.yjhh.ppwcustomer.view.CouponView
import com.yjhh.ppwcustomer.view.MyMessageView
import com.yjhh.ppwcustomer.view.RecentlyBrowseView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.*
import java.util.function.Function
import kotlin.collections.ArrayList

class SectionUselessPresent(var context: Context) : BasePresent() {
    lateinit var recentlyBrowseView: RecentlyBrowseView

    lateinit var myMessageView: MyMessageView

    constructor(context: Context, recentlyBrowseView: RecentlyBrowseView) : this(context) {
        this.recentlyBrowseView = recentlyBrowseView
    }


    constructor(context: Context, myMessageView: MyMessageView) : this(context) {
        this.myMessageView = myMessageView
    }


    val model = SectionUselessModel()


    fun userhistory(status: String, pageIndex: Int, pageSize: Int, flag: String) {

        toSubscribe2(model.userhistory(status, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("coupon", response)

                val recentlyBrowseBean = gson.fromJson<RecentlyBrowseBean>(response, RecentlyBrowseBean::class.java)

                recentlyBrowseView.onSuccess(recentlyBrowseBean, flag)
            }

            override fun onFault(message: String) {
                Log.i("coupon", message)
                recentlyBrowseView.onFault(message)
            }

        })
    }


    fun usercollect(status: String, pageIndex: Int, pageSize: Int, flag: String) {

        toSubscribe2(model.usercollect(status, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("coupon", response)

                val recentlyBrowseBean = gson.fromJson<RecentlyBrowseBean>(response, RecentlyBrowseBean::class.java)

                recentlyBrowseView.onSuccess(recentlyBrowseBean, flag)
            }

            override fun onFault(message: String) {
                Log.i("coupon", message)
                recentlyBrowseView.onFault(message)
            }

        })
    }


    fun useraccount(status: String, pageIndex: Int, pageSize: Int, flag: String) {

        toSubscribe2(model.useraccount(status, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("coupon", response)

                val recentlyBrowseBean = gson.fromJson<RecentlyBrowseBean>(response, RecentlyBrowseBean::class.java)

                recentlyBrowseView.onSuccess(recentlyBrowseBean, flag)
            }

            override fun onFault(message: String) {
                Log.i("coupon", message)
                recentlyBrowseView.onFault(message)
            }

        })
    }


    fun useraccountindex() {
        toSubscribe2(model.useraccountindex(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("coupon", response)
                val recentlyBrowseBean = gson.fromJson<RecentlyBrowseBean>(response, RecentlyBrowseBean::class.java)
                // recentlyBrowseView.onSuccess(recentlyBrowseBean)
            }

            override fun onFault(message: String) {
                Log.i("coupon", message)
                //  recentlyBrowseView.onFault(message)
            }

        })
    }


    fun useraccountjoin(status: String, pageIndex: Int, pageSize: Int, flag: String) {

        val useraccount = model.useraccount(status, pageIndex, pageSize)

        val useraccountindex = model.useraccountindex()


        val dis = Observable.zip(useraccountindex, useraccount,
            BiFunction<ResponseBody, ResponseBody, List<String>> { t1, t2 ->

                val list = ArrayList<String>()
                Log.i("useraccountjoin", t1.string())
                Log.i("useraccountjoin", t2.string())

                val value1 = t1.string()
                val value2 = t2.string()
                val jsonObject1 = JSONObject(value1)
                val jsonObject2 = JSONObject(value2)

                if (t1.string() != null && jsonObject1.getBoolean("success")) {


                    list.add(jsonObject1.getString("data"))

                } else {
                    list.add("null")
                }


                if (t2.string() != null && jsonObject2.getBoolean("success")) {
                    list.add(jsonObject2.getString("data"))

                } else {
                    list.add("null")
                }

                list

            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({


            }, {

            })

    }


    fun usermessage(status: String, share: String, pageIndex: Int, pageSize: Int, flag: String) {

        toSubscribe2(model.usermessage(status, share, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("usermessage", response)

                val recentlyBrowseBean = gson.fromJson<MyMessageBean>(response, MyMessageBean::class.java)

                myMessageView.onSuccess(recentlyBrowseBean, flag)
            }

            override fun onFault(message: String) {
                Log.i("usermessage", message)
                myMessageView.onFault(message)
            }

        })
    }


}