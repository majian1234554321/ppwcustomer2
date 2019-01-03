package com.ppwc.restaurant.views

import android.text.TextUtils
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.mrcheckpayfragment.*

class MRCheckPayFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.mrcheckpayfragment

    override fun initView() {
        mb_buy.setOnClickListener {
            start(MRPendingPaymentFragment())
        }


        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            discountNoPrice.visibility = if (isChecked) View.VISIBLE else View.GONE
            view_line.visibility = if (isChecked) View.VISIBLE else View.GONE

        }


        val dis = RxTextView.textChanges(et_totleprice).subscribe {

            if (!TextUtils.isEmpty(it)) {

                if (!TextUtils.isEmpty(discountNoPrice.text.toString().trim())) {
                    tv_finalprice.text =
                            calculation(it.toString(), discountNoPrice.text.toString(), "1", "0").toString()
                } else {
                    tv_finalprice.text = calculation(it.toString(), "0", "1", "0").toString()
                }


            } else {
                tv_finalprice.text = "";
            }

        }


        val dis2 = RxTextView.textChanges(discountNoPrice).subscribe {
            if (!TextUtils.isEmpty(it)) {

                if (!TextUtils.isEmpty(et_totleprice.text.toString().trim())) {
                    tv_finalprice.text =
                            calculation(et_totleprice.text.toString(), it.toString(), "1", "0").toString()
                } else {
                    tv_finalprice.text = ""
                }


            } else {
                if (!TextUtils.isEmpty(et_totleprice.text.toString().trim())) {
                    tv_finalprice.text =
                            calculation(et_totleprice.text.toString(), "0", "1", "0").toString()
                } else {
                    tv_finalprice.text = ""
                }
            }
        }


        compositeDisposable.add(dis)
        compositeDisposable.add(dis2)

    }


    fun calculation(totleprice: String, discountNoPrice: String, flag: String?, discountValue: String): Float {


        var price = 0f

        if ("1" == flag) {   //  类型（0满减（面值）1 抵扣（折扣百分比））
            if ("0" == discountValue) {  //“0” 不打折扣
                price = (totleprice.toFloat() - discountNoPrice.toFloat())
            } else {
                price = (totleprice.toFloat() - discountNoPrice.toFloat()) * discountValue.toFloat() +
                        discountNoPrice.toFloat()
            }

        } else {
            price = totleprice.toFloat() - discountNoPrice.toFloat() - discountValue.toFloat() +
                    discountNoPrice.toFloat()
        }


        return price
    }


}