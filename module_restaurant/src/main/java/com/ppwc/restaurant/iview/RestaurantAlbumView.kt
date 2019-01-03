package com.ppwc.restaurant.iview

import com.ppwc.restaurant.mrbean.RestaurantAlbumBean
import com.yjhh.common.base.BaseView

interface RestaurantAlbumView :BaseView {
    fun onRestaurantAlbumValue(model : RestaurantAlbumBean)
}