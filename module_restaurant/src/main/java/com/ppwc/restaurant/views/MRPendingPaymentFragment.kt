package com.ppwc.restaurant.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.jakewharton.rxbinding2.view.RxView
import com.ppwc.restaurant.R


import com.yjhh.common.Constants
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.DisplayPayTypeBean
import com.yjhh.common.iview.PayView
import com.yjhh.common.model.WxPayBean
import com.yjhh.common.pay.RxPay
import com.yjhh.common.present.PayPresent
import com.yjhh.common.utils.APKVersionCodeUtils
import com.yjhh.common.utils.DateUtil
import com.yjhh.common.utils.RxCountDown
import kotlinx.android.synthetic.main.mrpendingpaymentfragment.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class MRPendingPaymentFragment : BaseFragment(), PayView {


    override fun getLayoutRes(): Int = R.layout.mrpendingpaymentfragment


    companion object {
        fun newInstance(jsonValue: String?): MRPendingPaymentFragment {
            val fragment = MRPendingPaymentFragment()
            val bundle = Bundle()

            bundle.putString("jsonValue", jsonValue)
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onFault(errorMsg: String?) {

    }

    override fun onAliPayValue(value: String?) {

        if (value != null) {
            val jsonObject = JSONObject(value)
            val str = jsonObject.optString("data")
            val id = jsonObject.optString("id")

            val disposable = RxPay(mActivity).requestAlipay(str)
                .subscribe({ aBoolean ->
                    Log.e("accept:Ali", "accept: " + aBoolean!!)


                    if (aBoolean) {
                        toDispatchSuccess(id, "2")
                    } else {
                        toDispatchError(id, "2")
                    }


                }) { throwable ->
                    Log.e("accept:Ali", "accept: " + throwable.toString())
                }
            compositeDisposable.add(disposable)
        }


    }

    override fun onWxPayValue(value: String?) {


        if (value != null) {

            val gson = Gson()
            val model = gson.fromJson<WxPayBean>(value, WxPayBean::class.java)

            val model2 = WxPayBean(
                model.partnerid,
                model.prepayid,
                model.packagestr,
                model.noncestr,
                model.timestamp,
                model.paysign
            )

            val disposable = RxPay(mActivity).requestWXpay(gson.toJson(model2))
                .subscribe({ aBoolean ->
                    Log.e("accept:WX", aBoolean.toString())
                    if (aBoolean) {

                        when (type) {
                            "限时抢拍" -> {
                                startWithPop(MRPaySuccessFragment()) //	//1微信 2支付宝 4银联
                            }

                            "道具购买" -> {
                                // startWithPop(PayResultFragment.newInstance(model.id, "1")) //	//1微信 2支付宝 4银联
                            }


                            else -> {

                                startWithPop(MRPaySuccessFragment())
                            }
                        }


                    } else {
//                        startWithPop(PayResultFragment.newInstance(model.id, "1")) //	//1微信 2支付宝 4银联
//                        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show()

                    }
                }) { throwable ->

                    Log.e("accept:WX ", throwable.toString())
                }
            compositeDisposable.add(disposable)


        }

        /**************************
        {
        "nonceStr": "必填项",
        "partnerId": "必填项",
        "packageValue": "必填项",
        "prepayId": "必填项",
        "sign": "必填项",
        "timeStamp": "必填项"
        }
         *
         * */


    }

    var payPresent: PayPresent? = null

    var type: String? = null


    override fun initView() {


        payPresent = PayPresent(mActivity, this)


        val jsonString = arguments?.getString("jsonValue")
        type = arguments?.getString("type")

        val gson = Gson()
        val model = gson.fromJson<DisplayPayTypeBean>(jsonString, DisplayPayTypeBean::class.java)
        tv_price.text = mActivity.getString(R.string.rmb_price_double2, model.money)

        tv_pay.text = "确认支付（${mActivity.getString(R.string.rmb_price_double2, model.money)}）"


        val dis5 = RxCountDown.countdown(900).subscribe {
            //            if (it != null && tv_countdown != null)
//                tv_countdown.text = DateUtil.getFormatDHMmDate(it)
        }

        compositeDisposable.add(dis5)

        //客户端固定三种方式  微信 1 支付宝2 银联 4
        if (1 and model.suppPayType == 1) {
            rb_wxpay.visibility = View.VISIBLE
        } else {
            rb_wxpay.visibility = View.GONE
        }

        if (2 and model.suppPayType == 2) {
            rb_alipay.visibility = View.VISIBLE
        } else {
            rb_alipay.visibility = View.GONE
        }


        val dis = RxView.clicks(tv_pay).throttleFirst(2, TimeUnit.SECONDS)
            .subscribe {
                if (rb_alipay.isChecked) {

                    payPresent?.paymentByAli(model.id, model.money.toString())

                } else {
                    if (APKVersionCodeUtils.isWeChatAppInstalled(mActivity)) {
                        payPresent?.paymentByWx(model.id, model.money.toString())
                    } else {
                        Toast.makeText(mActivity, "请安装微信", Toast.LENGTH_LONG).show()
                    }

                }
            }

        compositeDisposable.add(dis)


    }


    fun toDispatchSuccess(id: String?, type: String?) {
        startWithPop(MRPaySuccessFragment())
    }

    fun toDispatchError(id: String?, type: String?) {
        // startWithPop(PayResultFragment.newInstance(id, type)) //	//1微信 2支付宝 4银联
        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show()
    }

}
