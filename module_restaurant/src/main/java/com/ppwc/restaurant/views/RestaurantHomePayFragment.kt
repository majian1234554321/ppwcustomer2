package com.ppwc.restaurant.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.ppwc.restaurant.bean.ShopPayPageInitModel
import com.ppwc.restaurant.iview.PrePayCheckServie
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.TextStyleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.restaurantorderdetailsfragment.*

class RestaurantHomePayFragment :BaseFragment() {
    override fun getLayoutRes(): Int  = R.layout.restauranthomepayfragment
    var shopId: String? = null
    override fun initView() {
        ImmersionBar.setTitleBar(activity, tbv_title)



        val couponId = arguments?.getString("couponId")
        val orderId = arguments?.getString("orderId")
        val typeId = arguments?.getString("typeId")
        shopId = arguments?.getString("shopId")





        val model = ShopPayPageInitModel()

        model.couponId = couponId //卡券id
        model.orderId = orderId //订单id
        model.shopId = shopId //订单id
        model.type = "1"
        /*  1、商家首页，立即买单（页面A）
                2、卡券中心，我要使用（页面A）
                3、订单列表，立即使用（页面B）
                4、待定
                5、待定
                其他视为非法请求*/



        ApiServices.getInstance().create(PrePayCheckServie::class.java).pay(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("ShopPayPageInitModel", response)

                    val mrBean = Gson().fromJson<MRCheckPayFragment.MRCheckPayBean>(response, MRCheckPayFragment.MRCheckPayBean::class.java)

                    ImageLoaderUtils.load(mActivity,iv_image,mrBean.shopName,R.drawable.icon_place_square,R.drawable.icon_place_square,0)


                }

                override fun onFault(message: String) {

                }

            })







    }


    companion object {
        fun newInstance(couponId: String?, orderId: String?, shopId: String?, typeId: String?): RestaurantHomePayFragment {
            val fragment = RestaurantHomePayFragment()
            val bundle = Bundle()

            bundle.putString("couponId", couponId)
            bundle.putString("orderId", orderId)
            bundle.putString("shopId", shopId)
            bundle.putString("typeId", typeId)



            fragment.arguments = bundle
            return fragment
        }
    }
}