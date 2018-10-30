package com.yjhh.common.api

import android.content.Context
import com.yjhh.common.base.WaitProgressDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody


abstract class ProcessObserver2(var context: Context) : Observer<ResponseBody> {
    private var showProgress: Boolean = true
    // private var progressDialog: WaitProgressDialog = WaitProgressDialog(context)
    override fun onSubscribe(d: Disposable) {

    }

    constructor(context: Context, showProgress: Boolean) : this(context) {
        this.showProgress = showProgress
    }

    override fun onNext(t:ResponseBody) {

     val response =  t.string()


        processValue(response)

    }

    abstract fun processValue(response: String?)

    abstract fun onFault(message: String)

    override fun onError(e: Throwable) {
        onFault(e.toString())
    }

    override fun onComplete() {

    }


    private fun showProgressDialog() {
        if (showProgress) {
        }
        //   progressDialog.show()
    }

    private fun dismissProgressDialog() {
        if (showProgress) {

        }
        // progressDialog.dismiss()
    }

}

