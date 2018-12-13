package com.yjhh.ppwcustomer.present

import android.content.Context
import androidx.collection.ArrayMap
import android.util.Log
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent


import com.yjhh.ppwcustomer.apis.SectionEvluateService
import com.yjhh.ppwcustomer.view.EvaluateView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EvaluatePresent(var context: Context, var view: EvaluateView) : BasePresent() {


    fun allcomments(type: String, isHasfile: String, startIndex: Int, pageSize: Int, flag: String) {

        val map = androidx.collection.ArrayMap<String, String>()
        map["type"] = type//类别，默认null（null/0全部 1好评 2中评 3差评）
        map["hasFile"] = isHasfile//是否包含附件，默认null（null/0 全部 1包含附件）
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


