package com.paipaiwei.takeout_personal.present

import android.content.Context
import android.util.Log
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.present.BasePresent
import com.paipaiwei.takeout_personal.bean.CouponBean
import com.paipaiwei.takeout_personal.model.SectionCouponModel
import com.paipaiwei.takeout_personal.view.CouponView

class SectionCouponPresent(var context: Context, var couponView: CouponView) : BasePresent() {

    val model = SectionCouponModel()


    fun coupon(status: String, pageIndex: Int, pageSize: Int, flag: String) {

        toSubscribe2(model.coupon(status, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("coupon", response)

                val couponBean = gson.fromJson<CouponBean>(response, CouponBean::class.java)

                couponView.onSuccess(couponBean, flag)
            }

            override fun onFault(message: String) {
                Log.i("coupon", message)
                couponView.onFault(message)
            }

        })
    }
}