package com.paipaiwei.takeout_personal.view

import com.yjhh.common.base.BaseView
import com.paipaiwei.takeout_personal.bean.MyAddressBean


interface MyAddressView:BaseView {
     fun onSuccess(main1bean: MyAddressBean, flag:String)

    override fun onFault(errorMsg: String?)
}
