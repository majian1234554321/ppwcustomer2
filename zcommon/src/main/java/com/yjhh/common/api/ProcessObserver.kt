package com.yjhh.common.api

import android.content.Context
import com.yjhh.common.base.WaitProgressDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class ProcessObserver<T>(var context: Context) : Observer<BaseResponse<T>> {
    private var showProgress: Boolean = true
    private var progressDialog: WaitProgressDialog = WaitProgressDialog(context)
    override fun onSubscribe(d: Disposable) {

    }

    constructor(context: Context, showProgress: Boolean) : this(context) {
        this.showProgress = showProgress
    }

    override fun onNext(tBaseResponse: BaseResponse<T>) {
        if (tBaseResponse.success) {
            onSuccess(tBaseResponse.data)
        } else {
            onFault(tBaseResponse.message)
        }

    }

    abstract fun onSuccess(data: T)

    abstract fun onFault(message: String)

    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }


    private fun showProgressDialog() {
        if (showProgress)
            progressDialog.show()
    }

    private fun dismissProgressDialog() {
        if (showProgress)
            progressDialog.dismiss()
    }

}

