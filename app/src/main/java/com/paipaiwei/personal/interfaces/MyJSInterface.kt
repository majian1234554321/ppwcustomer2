package com.paipaiwei.personal.interfaces

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import com.alibaba.android.arouter.launcher.ARouter
import com.paipaiwei.personal.ui.activity.onepay.OnePayFragment
import com.paipaiwei.personal.ui.fragment.BackViewFragment

import org.json.JSONObject

class MyJSInterface(var context: Context) {

    @JavascriptInterface
    fun shareToWeChat(json: String?) {
        Log.i("TAG", json)
        val jsonObject = JSONObject(json)
    }


    @JavascriptInterface
    fun login(json: String?) {
        Log.i("TAG:login", json)
        val jsonObject = JSONObject(json)
    }


    @JavascriptInterface
    fun viewOrder(json: String?) {
        Log.i("TAG:viewOrder", json)


        ARouter.getInstance()
            .build("/RestaurantActivity/Restaurant")
            .withString("displayTab", "RestaurantOrderDetailsFragment")
            .withString("id", json.toString())
            .navigation()


    }


    @JavascriptInterface
    fun viewCoupon(json: String?) {
        Log.i("TAG:viewCoupon", json)

        ARouter.getInstance()
            .build("/RestaurantActivity/Restaurant")
            .withString("displayTab", "RestaurantOrderDetailsFragment")
            .withString("id", json.toString())
            .navigation()
    }


    @JavascriptInterface
    fun bugProp(json: String?) {
        Log.i("TAG:bugProp", json)
       // val jsonObject = JSONObject(json)


       // ( context as  BackViewFragment).  start(OnePayFragment())

        ARouter.getInstance()
            .build("/DisplayActivity/Display")
            .withString("displayTab", "OnePayFragment")
            .withString("value", "浏览")
            .navigation(context)


    }


    @JavascriptInterface
    fun show(json: String?) {
        Log.i("TAG", json)
        val jsonObject = JSONObject(json)
    }

}