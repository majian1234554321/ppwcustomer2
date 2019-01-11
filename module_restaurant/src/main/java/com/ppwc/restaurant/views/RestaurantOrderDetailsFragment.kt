package com.ppwc.restaurant.views

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.ppwc.restaurant.R
import com.ppwc.restaurant.ipresent.RestaurantOrderPresent
import com.ppwc.restaurant.iview.RestaurantOrderSerVice
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.PhoneUtils
import com.yjhh.common.view.AlertDialogFactory
import com.yjhh.common.view.TitleBarView
import kotlinx.android.synthetic.main.restaurantorderdetailsfragment.*

class RestaurantOrderDetailsFragment : BaseFragment(), View.OnClickListener,
    RestaurantOrderSerVice.RestaurantOrderView {
    override fun onRestaurantOrder(model: String?) {

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


        val id = arguments?.getString("id")




        RestaurantOrderPresent(mActivity, this)









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