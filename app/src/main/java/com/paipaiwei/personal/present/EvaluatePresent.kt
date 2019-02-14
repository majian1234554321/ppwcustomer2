package com.paipaiwei.personal.present

import android.content.Context
import android.util.Log
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent


import com.yjhh.common.api.SectionEvluateService
import com.paipaiwei.personal.view.EvaluateView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EvaluatePresent(var context: Context, var view: EvaluateView) : BasePresent() {


    fun allcomments(startIndex: Int, pageSize: Int, flag: String) {


        map["pageIndex"] = startIndex.toString()
        map["pageSize"] = pageSize.toString()

        ApiServices.getInstance()
            .create(SectionEvluateService::class.java)
            .allcomments(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(context) {
                override fun onFault(message: String) {
                    Log.i("EvaluateManageFragment", message)
                    view.onFault(message)
                }

                override fun processValue(response: String?) {
                    Log.i("EvaluateManageFragment", response)
                    view.onSuccess(response, flag)

                }
            })
    }
}


