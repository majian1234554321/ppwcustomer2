package com.paipaiwei.personal.view

import com.yjhh.common.base.BaseView


interface EvaluateView : BaseView {
    fun onSuccess(value: String?,flag:String)
}