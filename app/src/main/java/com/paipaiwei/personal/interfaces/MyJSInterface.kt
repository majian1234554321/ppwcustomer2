package com.paipaiwei.personal.interfaces

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface

import org.json.JSONObject

class MyJSInterface(var context: Context) {

    @JavascriptInterface
    fun shareToWeChat(json: String?) {
        Log.i("TAG", json)
        val jsonObject = JSONObject(json)
    }

}