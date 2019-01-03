package com.ppwc.restaurant.views

import android.widget.Toast
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.mrpendingpaymentfragment.*

class MRPendingPaymentFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.mrpendingpaymentfragment

    override fun initView() {
        tv_pay.setOnClickListener {
            start(MRPaySuccessFragment())
        }





        tv_pay.setOnClickListener {


            if (rb_alipay.isChecked) {
                "alipay"
            } else {
                "wxpay"
            }





            Toast.makeText(
                mActivity, if (rb_alipay.isChecked) {
                    "alipay"
                } else {
                    "wxpay"
                }, Toast.LENGTH_SHORT
            ).show()
        }

    }

}
