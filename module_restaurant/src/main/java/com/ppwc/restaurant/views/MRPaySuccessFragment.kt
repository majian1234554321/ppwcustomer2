package com.ppwc.restaurant.views

import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.mrpaysuccessfragment.*

class MRPaySuccessFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.mrpaysuccessfragment

    override fun initView() {
        rl7.setOnClickListener {
            start(OrderEvaluationFragment())
        }
    }

}