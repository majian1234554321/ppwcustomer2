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