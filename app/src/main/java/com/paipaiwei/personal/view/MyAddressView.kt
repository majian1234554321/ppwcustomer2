package com.paipaiwei.personal.view

import com.yjhh.common.base.BaseView
import com.paipaiwei.personal.bean.MyAddressBean


interface MyAddressView:BaseView {
     fun onSuccess(main1bean: MyAddressBean, flag:String)

    override fun onFault(errorMsg: String?)
}
