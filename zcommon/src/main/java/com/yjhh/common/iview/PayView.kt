package com.yjhh.common.iview

import com.yjhh.common.base.BaseView

interface PayView: BaseView {
    fun onWxPayValue(value:String ?)
    fun onAliPayValue(value:String ?)
}