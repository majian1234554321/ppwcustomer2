package com.ppwc.restaurant.views

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.ppwc.restaurant.R
import com.ppwc.restaurant.bean.OrderDetailsBean
import com.ppwc.restaurant.ipresent.RestaurantOrderPresent
import com.ppwc.restaurant.iview.RestaurantOrderSerVice
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.PhoneUtils
import com.yjhh.common.utils.TimeUtil
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.common.view.TitleBarView
import kotlinx.android.synthetic.main.restaurantorderdetailsfragment.*

class RestaurantOrderDetailsFragment : BaseFragment(), View.OnClickListener,
    RestaurantOrderSerVice.RestaurantOrderView {
    override fun onRestaurantOrder(model: String?) {


        val orderDetailsBean = Gson().fromJson<OrderDetailsBean>(model, OrderDetailsBean::class.java)

        iev1.setTextContent(TimeUtil.stampToDate(orderDetailsBean.createdTime))
        iev2.setTextContent(orderDetailsBean.orderNo)
        iev3.setTextContent(orderDetailsBean.totalMoney.toString())
        iev4.setTextContent(orderDetailsBean.money.toString())
        iev5.setTextContent(TimeUtil.stampToDate(orderDetailsBean.finishTime))
//        iev6.setTextContent()
//        iev7.setTextContent()
//        iev8.setTextContent()
//        iev9.setTextContent()
//        iev10.setTextContent()

        tv_storeName.text = orderDetailsBean.shopName
        ImageLoaderUtils.loadCircle(
            mActivity,
            iv_image,
            orderDetailsBean.shopLogoUrl,
            R.drawable.icon_place_pai,
            R.drawable.icon_place_pai
        )



    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_1 -> {

            }
            R.id.mb_2 -> {
                start(OrderEvaluationFragment())
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.restaurantorderdetailsfragment

    override fun initView() {

        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {
                AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                    null,
                    "确认删除此订单吗？",
                    "确定", "取消",
                    { dlg, v ->

                    }, { dlg, v ->
                    })

            }

        })


        val orderId = arguments?.getString("id")




        RestaurantOrderPresent(mActivity, this).detail(orderId)









        arrayOf(mb_1, mb_2).forEach {
            it.setOnClickListener(this)
        }


        mb_2.backgroundTintList = ColorStateList.valueOf(Color.RED)
        mb_1.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)

    }


    companion object {
        fun newInstance(id: String?): RestaurantOrderDetailsFragment {

            val fragment = RestaurantOrderDetailsFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment


        }
    }


}