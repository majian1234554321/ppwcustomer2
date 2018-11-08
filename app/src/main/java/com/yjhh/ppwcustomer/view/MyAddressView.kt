package com.yjhh.ppwcustomer.view

import com.yjhh.common.base.BaseView
import com.yjhh.ppwcustomer.bean.MyAddressBean


interface MyAddressView:BaseView {
     fun onSuccess(main1bean: MyAddressBean, flag:String)

    override fun onFault(errorMsg: String?)
}
