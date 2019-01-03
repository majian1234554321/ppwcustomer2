package com.ppwc.restaurant.iview

import com.ppwc.restaurant.mrbean.RestaurantHomeBean
import com.yjhh.common.base.BaseView

interface RestaurantView :BaseView {
    fun onRestaurantValue(model : RestaurantHomeBean)
}