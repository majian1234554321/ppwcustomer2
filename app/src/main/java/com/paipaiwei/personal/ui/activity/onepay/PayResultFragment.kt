package com.paipaiwei.personal.ui.activity.onepay

import android.os.Bundle
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.PayResultBean
import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.view.OrderView
import com.yjhh.common.base.BaseFragment

import kotlinx.android.synthetic.main.payresultfragment.*

class PayResultFragment : BaseFragment(), OrderView {
    override fun onSuccessOrder(response: String?, flag: String?) {

        val model = Gson().fromJson<PayResultBean>(response, PayResultBean::class.java)


        tv_tips.text = model.payStatusText
        tv_title.text = model.payStatusText
        iev1.setTextContent("")
        iev2.setTextContent("$")
        iev3.setTextContent("${mActivity.getString(R.string.rmb_price_double2, model.money)}")

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.payresultfragment
    var orderPresent: OrderPresent? = null
    override fun initView() {

        ImmersionBar.setTitleBar(mActivity, toolbar)
        iv_right.setOnClickListener { mActivity.onBackPressed() }
        mb.setOnClickListener { mActivity.onBackPressed() }

        val payType = arguments?.getString("payType")
        val ids = arguments?.getString("ids")
        orderPresent = OrderPresent(mActivity, this)
        orderPresent?.detailFromCallback(ids, payType)    //1微信 2支付宝 4银联
    }


    companion object {
        fun newInstance(ids: String?, payType: String?): PayResultFragment {
            val fragment = PayResultFragment()
            val bundle = Bundle()
            bundle.putString("ids", ids)
            bundle.putString("payType", payType)
            fragment.arguments = bundle
            return fragment
        }
    }

}