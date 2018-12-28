package com.ppwc.restaurant.iview

import com.ppwc.restaurant.bean.MeiShiFootBean
import com.ppwc.restaurant.bean.MeiShiHeadBean
import com.yjhh.common.base.BaseView

interface MeiShiHeadView :BaseView {
    fun MeiShiHeadValue(model :MeiShiHeadBean)

    fun MeiShiFootValue(model : MeiShiFootBean)
}
