package com.yjhh.common.api

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.TextUtils
import android.widget.Toast
import com.google.gson.Gson
import com.yjhh.common.base.WaitProgressDialog
import com.yjhh.common.utils.LogUtils
import com.yjhh.common.utils.SharedPreferencesUtils
import com.yjhh.common.view.AlertDialogFactory
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject
import java.net.ConnectException


abstract class ProcessObserver2(var context: Context) : Observer<ResponseBody> {
    private var showProgress: Boolean = false

    private var dialog: AlertDialog? = null


    object constructor {
        val gson = Gson()
    }


    override fun onSubscribe(d: Disposable) {
        if (showProgress) {

        }
    }


    constructor(context: Context, showProgress: Boolean) : this(context) {
        this.showProgress = showProgress
        dialog = AlertDialogFactory.createFactory(context).getLoadingDialog("加载中...")
        // dialog?.setCancelable(false)
    }

    override fun onNext(t: ResponseBody) {



        val response = t.string()

        LogUtils.i("ProcessObserver2", response)
        if (!TextUtils.isEmpty(response) && response.contains("success")) {
            val jsonValue = JSONObject(response)
            if (jsonValue.getBoolean("success")) {
                val jsonString = jsonValue.getString("data")
                processValue(jsonString)
            } else {


                if ("01001" == jsonValue.optString("code")) {
                    Toast.makeText(context, "请重新登录", Toast.LENGTH_SHORT).show()
                    SharedPreferencesUtils.setParam(context, "mobile", "")
                    SharedPreferencesUtils.setParam(context, "nickName", "")
                    SharedPreferencesUtils.setParam(context, "sessionId", "")
                    SharedPreferencesUtils.setParam(context, "type", "")
                    onFault("01001")
                    // context.startActivity(Intent(context, LoginActivity::class.java))
                   // (context as Activity).finish()
                } else if ("01018" == jsonValue.optString("code")) {
                    onFault("01018")
                } else {
                    onFault(jsonValue.getString("message"))
                }
            }


        } else {
            onFault(response)
        }


    }

    abstract fun processValue(response: String?)

    abstract fun onFault(message: String)

    override fun onError(e: Throwable) {

        if (e is ConnectException){
            onFault("连接服务器失败")
        }else{
            onFault(e.toString())
        }





    }

    override fun onComplete() {
        if (showProgress){
            SystemClock.sleep(1000)
        }

         dismissProgressDialog()
    }


    private fun showProgressDialog() {
        if (showProgress) {

        }

    }

    private fun dismissProgressDialog() {
        if (showProgress) {
            dialog?.dismiss()
        }
        // progressDialog.dismiss()
    }

}


