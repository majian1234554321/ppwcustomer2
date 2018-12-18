package com.paipaiwei.personal.view

import com.yjhh.common.base.BaseView


interface OrderView : BaseView {

    fun onSuccessOrder(response: String?, flag: String?)

}