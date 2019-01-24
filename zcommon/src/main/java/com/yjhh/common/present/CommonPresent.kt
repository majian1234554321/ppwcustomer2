package com.yjhh.common.present

import android.content.Context
import android.util.Log

import com.google.gson.Gson

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.StringBuilder
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2


import com.yjhh.common.api.CommonService
import com.yjhh.common.iview.CommonView
import com.yjhh.common.model.InitBean
import com.yjhh.common.utils.SharedPreferencesUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CommonPresent(var context: Context, var view: CommonView) : BasePresent() {

    fun UpLoadFile(file: File) {

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("multipartFile", file.name, requestFile)


        toSubscribe2(
            ApiServices.getInstance().create(CommonService::class.java)
                .uploadFile(body), object : ProcessObserver2(context, true) {
                override fun processValue(response: String?) {

                    var sb = StringBuilder()
                    sb.append("{\"item\":").append(response).append("}")
                    Log.i("UpLoadFile", sb.toString())
                    view.onSuccess(sb.toString())
                }

                override fun onFault(message: String) {
                    Log.i("UpLoadFile", message)
                    view.onFault(message)
                }
            }
        )


    }

    fun UpLoadFiles(files: List<File?>) {


        val list = ArrayList<MultipartBody.Part>()


        files.forEachIndexed { index, it ->
            run {
                val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), it)
                val body = MultipartBody.Part.createFormData("multipartFile$index", it?.name, requestFile)
                list.add(body)
            }

        }
        toSubscribe2(ApiServices.getInstance().create(CommonService::class.java)
            .uploadFiles(list), object : ProcessObserver2(context, true) {
            override fun processValue(response: String?) {

                var sb = StringBuilder()
                sb.append("{\"item\":").append(response).append("}")
                Log.i("UpLoadFile", sb.toString())
                view.onSuccess(sb.toString())
            }

            override fun onFault(message: String) {
                Log.i("UpLoadFile", message)
                view.onFault(message)
            }
        }
        )


    }

    fun checkVersion() {
        ApiServices.getInstance()
            .create(CommonService::class.java)
            .version()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("MainActivity", response)

                    view.onSuccess(response)


                }

                override fun onFault(message: String) {
                    Log.i("MainActivity", message)
                    view.onFault(message)

                }

            })

    }

    fun init() {
        ApiServices.getInstance()
            .create(CommonService::class.java)
            .init()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("CommonPresent", response)
                    val model = Gson().fromJson<InitBean>(response, InitBean::class.java)
                    SharedPreferencesUtils.setParam(context, "applyShopUrl", model.applyShopUrl)
                    SharedPreferencesUtils.setParam(context, "couponRuleUrl", model.couponRuleUrl)
                    SharedPreferencesUtils.setParam(context, "helpIndexUrl", model.helpIndexUrl)

                }

                override fun onFault(message: String) {
                    Log.i("CommonPresent", message)
                }
            })
    }

    fun collect(itemType: String?, itemId: String?) {
        map.clear()
        map.put("itemType", itemType)
        map.put("itemId", itemId)

        toSubscribe2(
            ApiServices.getInstance().create(CommonService::class.java)
                .collect(map), object : ProcessObserver2(context, true) {
                override fun processValue(response: String?) {
                    Log.i("collect", response)
                    view.onSuccess(response)
                }

                override fun onFault(message: String) {
                    Log.i("collect", message)
                    view.onFault(message)
                }
            }
        )


    }

    fun zan() {

    }

}