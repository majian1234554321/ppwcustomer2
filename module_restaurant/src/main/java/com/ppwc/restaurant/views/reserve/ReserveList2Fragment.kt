package com.ppwc.restaurant.views.reserve

import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.reservelistfragment.*

class ReserveList2Fragment :BaseFragment() {
    override fun getLayoutRes(): Int  = R.layout.reservelistfragment

    override fun initView() {

        ImmersionBar.setTitleBar(mActivity,tbv_title)



    }
}