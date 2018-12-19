package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.SignBean
import com.paipaiwei.personal.bean.SignResultBean
import com.yjhh.common.base.BaseView


interface SignView : BaseView {

    fun onSuccessList(response: SignBean?)
    fun onSuccessSign(response: SignResultBean?)
}