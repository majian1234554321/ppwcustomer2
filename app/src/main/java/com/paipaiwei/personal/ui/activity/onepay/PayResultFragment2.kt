package com.paipaiwei.personal.ui.activity.onepay

import android.os.Bundle
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.PayResultBean
import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.view.OrderView
import com.yjhh.common.base.BaseFragment


import kotlinx.android.synthetic.main.payresultfragment2.*

class PayResultFragment2 : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.payresultfragment2

    override fun initView() {

        ImmersionBar.setTitleBar(mActivity, toolbar)
        mb_back.setOnClickListener {
            mActivity.onBackPressed()
        }
    }


    companion object {
        fun newInstance(ids: String?, payType: String?): PayResultFragment2 {
            val fragment = PayResultFragment2()
            val bundle = Bundle()
            bundle.putString("ids", ids)
            bundle.putString("payType", payType)
            fragment.arguments = bundle
            return fragment
        }
    }

}