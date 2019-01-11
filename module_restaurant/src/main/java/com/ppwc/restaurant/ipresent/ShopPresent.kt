package com.ppwc.restaurant.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.ppwc.restaurant.apis.ShopService
import com.ppwc.restaurant.bean.ProductDetailsBean
import com.ppwc.restaurant.iview.ProductDetailsView
import com.ppwc.restaurant.iview.RecommendProductView
import com.ppwc.restaurant.iview.RestaurantAlbumView
import com.ppwc.restaurant.iview.RestaurantView
import com.ppwc.restaurant.mrbean.RecommendProductBean
import com.ppwc.restaurant.mrbean.RestaurantAlbumBean
import com.ppwc.restaurant.mrbean.RestaurantHomeBean
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseView
import com.yjhh.common.present.BasePresent

class ShopPresent(var context: Context) : BasePresent() {


    var restaurantView: RestaurantView? = null

    constructor(context: Context, restaurantView: RestaurantView) : this(context) {
        this.context = context
        this.restaurantView = restaurantView
    }


    fun shop(id: String) {
        map.clear()
        map["id"] = id
        toSubscribe2(ApiServices.getInstance().create(ShopService::class.java).shop(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    Log.i("shop", response)

                    val model = gson.fromJson<RestaurantHomeBean>(response, RestaurantHomeBean::class.java)
                    restaurantView?.onRestaurantValue(model)

                }

                override fun onFault(message: String) {
                    Log.i("shop", message)
                    restaurantView?.onFault(message)
                }

            })
    }


    var restaurantAlbumView: RestaurantAlbumView? = null

    constructor(context: Context, restaurantAlbumView: RestaurantAlbumView) : this(context) {
        this.context = context
        this.restaurantAlbumView = restaurantAlbumView
    }

    fun images(shopId: String, type: String, pageIndex: Int, pageSize: Int) {
        map.clear()
        map["shopId"] = shopId
        map["type"] = type  //类型 null 默认 0 logo 1背景墙 2商品 3证件 4法人 5、1+2 6、3+4 7、123 8、1234
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        toSubscribe2(ApiServices.getInstance().create(ShopService::class.java).images(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("images", response)

                    val model = gson.fromJson<RestaurantAlbumBean>(response, RestaurantAlbumBean::class.java)
                    restaurantAlbumView?.onRestaurantAlbumValue(model)

                }

                override fun onFault(message: String) {
                    Log.i("images", message)
                    restaurantAlbumView?.onFault(message)
                }

            })
    }


    var recommendProductView: RecommendProductView? = null

    constructor(context: Context, recommendProductView: RecommendProductView) : this(context) {
        this.context = context
        this.recommendProductView = recommendProductView
    }

    fun products(shopId: String, pageIndex: Int, pageSize: Int,flag:String) {
        map.clear()
        map["shopId"] = shopId
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        toSubscribe2(ApiServices.getInstance().create(ShopService::class.java).products(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    val model = gson.fromJson<RecommendProductBean>(response, RecommendProductBean::class.java)

                    recommendProductView?.onRecommendProductValue(model,flag)

                }

                override fun onFault(message: String) {
                    recommendProductView?.onFault(message)
                }

            })
    }


    var productDetailsView: ProductDetailsView? = null

    constructor(context: Context, productDetailsView: ProductDetailsView) : this(context) {
        this.context = context
        this.productDetailsView = productDetailsView
    }

    fun product(id: String?) {
        map.clear()
        map["id"] = id
        toSubscribe2(ApiServices.getInstance().create(ShopService::class.java).product(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    val modle = gson.fromJson<ProductDetailsBean>(response, ProductDetailsBean::class.java)
                    productDetailsView?.onProductDetailsValue(modle)
                }

                override fun onFault(message: String) {
                    productDetailsView?.onFault(message)
                }

            })
    }

}