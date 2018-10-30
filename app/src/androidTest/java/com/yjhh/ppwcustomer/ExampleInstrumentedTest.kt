package com.yjhh.ppwcustomer

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.json.JSONObject

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        // Context of the app under test.
        val value = "{\n" +
                "    \"data\": \"[{\"beforeName\":\"Screenshot_20181026-112954_铁路12306.jpg\",\"contentType\":\"multipart/form-data\",\"ext\":\".jpg\",\"fileName\":\"08760da57abed336.jpg\",\"id\":\"2325E010DE2CA782CA1E6548EEC4776F\",\"path\":\"http://192.168.2.200:8080/file/20181030/08760da57abed336.jpg\",\"postName\":\"multipartFile\",\"size\":632198}]\",\n" +
                "    \"success\": true\n" +
                "}"







        val jsonString2 = JSONObject(value)

        val  s =   jsonString2.getString("data")


        //   val id = jsonString2.getString("id")
        Log.i("ExampleUnitTest", s)
    }
}
