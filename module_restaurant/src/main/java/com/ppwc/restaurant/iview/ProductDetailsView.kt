package com.ppwc.restaurant.iview

import com.ppwc.restaurant.bean.ProductDetailsBean
import com.yjhh.common.base.BaseView

interface ProductDetailsView : BaseView {

    fun onProductDetailsValue(model: ProductDetailsBean)

}