package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.NearByDataBean
import com.paipaiwei.personal.bean.NearbyBean
import com.yjhh.common.base.BaseView

interface NearbyView : BaseView {
    fun onNearby(model: NearbyBean)


    fun onNearbyData(model: NearByDataBean)
}