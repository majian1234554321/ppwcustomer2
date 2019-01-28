package com.paipaiwei.personal.interfaces

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import com.alibaba.android.arouter.launcher.ARouter

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
        val jsonObject = JSONObject(json)

        ARouter.getInstance()
            .build("/RestaurantActivity/Restaurant")
            .withString("displayTab", "RestaurantOrderDetailsFragment")
            .withString("id", json)
            .navigation()


    }


    @JavascriptInterface
    fun viewCoupon(json: String?) {
        Log.i("TAG:viewCoupon", json)
        val jsonObject = JSONObject(json)
    }


    @JavascriptInterface
    fun bugProp(json: String?) {
        Log.i("TAG:bugProp", json)
        val jsonObject = JSONObject(json)
    }


    @JavascriptInterface
    fun show(json: String?) {
        Log.i("TAG", json)
        val jsonObject = JSONObject(json)
    }

}