package com.ppwc.restaurant.iview

import com.ppwc.restaurant.mrbean.RecommendProductBean
import com.yjhh.common.base.BaseView

interface RecommendProductView  :BaseView{
    fun  onRecommendProductValue(model : RecommendProductBean ,flag:String)
}