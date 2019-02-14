package com.ppwc.restaurant.views

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.ppwc.restaurant.R
import com.ppwc.restaurant.bean.OrderDetailsBean
import com.yjhh.common.bean.RxOrderBean
import com.ppwc.restaurant.ipresent.RestaurantOrderPresent
import com.ppwc.restaurant.iview.RestaurantOrderSerVice
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.RxCountDown
import com.yjhh.common.utils.TimeUtil
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.common.view.TitleBarView
import kotlinx.android.synthetic.main.restaurantorderdetailsfragment.*

class RestaurantOrderDetailsFragment : BaseFragment(), View.OnClickListener,
    RestaurantOrderSerVice.RestaurantOrderView {
    var jsonString: String? = null
    var statusValue = -1
    var orderId :String? = null
    var shopLogoUrl:String? = null
    var shopName:String? = null

    override fun onRestaurantOrder(model: String?, flag: String) {

        if ("detail" == flag) {
            jsonString = model
            val orderDetailsBean = Gson().fromJson<OrderDetailsBean>(model, OrderDetailsBean::class.java)

            if (!TextUtils.isEmpty(TimeUtil.stampToDate(orderDetailsBean.createdTime))) {
                iev1.setTextContent(TimeUtil.stampToDate(orderDetailsBean.createdTime))
                iev1.setTextColor(ContextCompat.getColor(mActivity, R.color.all_3))
            } else {
                iev1.visibility = View.GONE
            }


            iev2.setTextContent(orderDetailsBean.orderNo)
            iev2.setTextColor(ContextCompat.getColor(mActivity, R.color.all_3))

            iev3.setTextContent(context.getString(R.string.rmb_price_double2, orderDetailsBean.totalMoney))
            iev3.setTextColor(ContextCompat.getColor(mActivity, R.color.all_3))

            iev4.setTextContent(context.getString(R.string.rmb_price_double2, orderDetailsBean.money))



            if (!TextUtils.isEmpty(orderDetailsBean.payTime)) {
                iev5.setTextContent(TimeUtil.stampToDate(orderDetailsBean.payTime))
            } else {
                iev5.visibility = View.GONE
            }

            orderId = orderDetailsBean.id

             shopLogoUrl= orderDetailsBean.shopLogoUrl
             shopName= orderDetailsBean.shopName


            if (!TextUtils.isEmpty(orderDetailsBean.useTime)) {
                iev6.setTextContent(TimeUtil.stampToDate(orderDetailsBean.useTime)) //	消费 时间
            } else {
                iev6.visibility = View.GONE
            }

            if (orderDetailsBean.useTotalMoney != 0f) {
                iev7.setTextContent(orderDetailsBean.useTotalMoney.toString()) // 消费 金额
            } else {
                iev7.visibility = View.GONE
            }

            if (orderDetailsBean.useDisMoney != 0f) {
                iev8.setTextContent(orderDetailsBean.useDisMoney.toString()) //消费 抵扣金额
            } else {
                iev8.visibility = View.GONE
            }

            if (orderDetailsBean.useUnDisMoney != 0f) {
                iev9.setTextContent(orderDetailsBean.useUnDisMoney.toString()) // 不参与折扣金额/不参与优惠金额
            } else {
                iev9.visibility = View.GONE
            }

            if (orderDetailsBean.useMoney != 0f) {
                iev10.setTextContent(orderDetailsBean.useMoney.toString()) //消费 实付金额
            } else {
                iev10.visibility = View.GONE
            }



            rl_home.setOnClickListener {
                start(RestaurantHomeFragment.newInstance(orderDetailsBean.shopId))
            }



            tv_storeName.text = orderDetailsBean.shopName
            ImageLoaderUtils.loadCircle(
                mActivity,
                iv_image,
                orderDetailsBean.shopLogoUrl,
                R.drawable.icon_place_pai,
                R.drawable.icon_place_pai
            )

            statusValue = orderDetailsBean.status
            when (orderDetailsBean.status) { //（1待付款 2已取消 3已付款 4已完成 5配送中 6退款申请中 7已关闭 // 8待评价 9待使用 10已失效）
                1 -> {

                    val dis = RxCountDown.countdown(orderDetailsBean.times).subscribe {
                        if (mb_1 != null) {
                            mb_1.visibility = View.VISIBLE
                            if (it <= 0) {

                                mb_1.text = "已失效"
                                mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                                mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                                mb_2.visibility = View.GONE
                            } else {

                                mb_1.text = "剩余支付时间  ${TimeUtil.secondToTime(it.toLong())}"
                                mb_1.setTextColor(Color.parseColor("#FFffff"))
                                mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F9572D"))
                            }
                        }


                    }
                    compositeDisposable.add(dis)


                    mb_2.visibility = View.GONE
                }
                2 -> {

                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "已取消"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE


                }
                3 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "已付款"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }
                4 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "已完成"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }
                5 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "配送中"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }
                6 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "退款申请中"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }
                7 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "已关闭"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }
                8 -> {


                    mb_2.visibility = View.VISIBLE
                    mb_2.text = "待评价"
                    mb_2.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFC536"))

                    mb_1.visibility = View.GONE

                }

                9 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "待使用"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }

                10 -> {
                    mb_1.visibility = View.VISIBLE
                    mb_1.text = "已失效"
                    mb_1.setTextColor(Color.parseColor("#FFB5B5B5"))
                    mb_1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFE6E6E6"))

                    mb_2.visibility = View.GONE
                }
                else -> {
                }
            }


        } else {
            RxBus.default.post(RxOrderBean("RestaurantOrderDetailsFragment", true))
            mActivity.onBackPressed()
        }

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_1 -> {

                when (statusValue) { // //（1待付款 2已取消 3已付款 4已完成 5配送中 6退款申请中 7已关闭 // 8待评价 9待使用 10已失效）
                    1 -> {
                        start(MRPendingPaymentFragment.newInstance(jsonString))
                    }
                    else -> {
                        start(OrderEvaluationFragment.newInstance(shopLogoUrl,shopName,"",orderId))
                    }
                }


            }
            R.id.mb_2 -> {

                when (statusValue) { // //（1待付款 2已取消 3已付款 4已完成 5配送中 6退款申请中 7已关闭 // 8待评价 9待使用 10已失效）
                    8 -> {
                        start(OrderEvaluationFragment.newInstance(shopLogoUrl,shopName,"",orderId))
                    }
                    else -> {
                    }
                }

            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.restaurantorderdetailsfragment

    var present: RestaurantOrderPresent? = null


    override fun initView() {
        val orderId = arguments?.getString("id")
        present = RestaurantOrderPresent(mActivity, this)

        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {
                AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                    null,
                    "确认删除此订单吗？",
                    "确定", "取消",
                    { dlg, v ->
                        present?.del(orderId, "del")

                    }, { dlg, v ->
                    })

            }

        })

        present?.detail(orderId, "detail")

        arrayOf(mb_1, mb_2).forEach {
            it.setOnClickListener(this)
        }


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