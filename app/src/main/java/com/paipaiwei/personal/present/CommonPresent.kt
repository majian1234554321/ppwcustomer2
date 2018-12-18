package com.paipaiwei.personal.present

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity

import com.azhon.appupdate.utils.LogUtil
import com.google.gson.Gson

import io.reactivex.disposables.Disposable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.StringBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.paipaiwei.personal.apis.CommonService

import com.paipaiwei.personal.view.CommonView
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




    fun checkVersion(){
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

                }

            })

    }



   /* fun  applyShop(){
        ApiServices.getInstance()
            .create(ShopSetServices::class.java)
            .applyShop()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("01018", response)
                    if (response?.contains("\"")!!) {
                        val intent = Intent(mActivity, BackViewActivity::class.java)
                        intent.putExtra("url", response.replace("\"", ""))
                        startActivity(intent)
                    } else {
                        val intent = Intent(mActivity, BackViewActivity::class.java)
                        intent.putExtra("url", response)
                        startActivity(intent)
                    }

                }

                override fun onFault(message: String) {
                    Log.i("01018", message)
                }
            })
    }*/




}