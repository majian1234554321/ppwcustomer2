package com.ppwc.restaurant.views

import android.os.Bundle
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment

class ConsumptionLogFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.consumptionlogfragment

    override fun initView() {

    }


    companion object {
        fun newInstance(id: String?): ConsumptionLogFragment {

            val fragment = ConsumptionLogFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment


        }
    }

}