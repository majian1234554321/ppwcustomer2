package com.paipaiwei.personal.ui.activity.onepay

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.jakewharton.rxbinding2.view.RxView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.view.OrderView
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.WXAPIFactory

import com.yjhh.common.Constants
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.iview.PayView
import com.yjhh.common.model.WxPayBean
import com.yjhh.common.pay.RxPay
import com.yjhh.common.present.PayPresent

import kotlinx.android.synthetic.main.onepaymoneyfragment.*
import java.util.concurrent.TimeUnit

class OnePayMoneyFragment : BaseFragment(), PayView, OrderView {


    //1微信 2支付宝 4银联
    override fun onSuccessOrder(response: String?, flag: String?) {

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onAliPayValue() {
        val str =
            "partner=\"2088121059329235\"&seller_id=\"1993349866@qq.com\"&out_trade_no=\"XGJ_LIVE20171130142905-440402\"&subject=\"一对一收费单节\"&body=\"一对一收费单节\"&total_fee=\"0.01\"&notify_url=\"http://new.antwk.com/api/order/alipayNotify\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"1757281m\"&return_url=\"m.alipay.com\"&sign=\"vn%2Fw5wJAYSdP5rtQxumnAXPaaidyeVOluEoDlvS4axezmvfpoIHzwxj5pqNrJ5NMKq7NK8krHWBo8Z6jeTkFbCb2mvLbyBicAjDz02WyPOmKM%2F%2FGRfqfDlX4Q0T06PQmipNFVD3UPHrwPQbHG3eeWobqBFG0jcu%2FtnMZrsZvzso%3D\"&sign_type=\"RSA\""
        val disposable = RxPay(mActivity).requestAlipay(str)
            .subscribe({ aBoolean ->
                Log.e("accept:Ali", "accept: " + aBoolean!!)
            }) { throwable ->
                Log.e("accept:Ali", "accept: " + throwable.toString())
            }
        compositeDisposable.add(disposable)
    }

    override fun onWxPayValue(value: String?) {


        if (value != null) {

            val gson = Gson()
            val model = gson.fromJson<WxPayBean>(value, WxPayBean::class.java)
//
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
                        getOederSuccessDetails(model.id, "1")
                    } else {
                        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show()
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
    var orderPresent: OrderPresent? = null


    override fun getLayoutRes(): Int = R.layout.onepaymoneyfragment

    override fun initView() {
        payPresent = PayPresent(mActivity, this)

        orderPresent = OrderPresent(mActivity, this)


        val jsonString = arguments?.getString("jsonValue")
        val gson = Gson()
        val model = gson.fromJson<DisplayPayTypeBean>(jsonString, DisplayPayTypeBean::class.java)

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
                    payPresent?.paymentByWx(model.id, model.money.toString())
                }
            }

        compositeDisposable.add(dis)


    }


    fun getOederSuccessDetails(id: String, type: String) {
        orderPresent?.detailFromCallback(id, type)
    }


    companion object {
        fun newInstance(jsonValue: String?): OnePayMoneyFragment {
            val fragment = OnePayMoneyFragment()
            val bundle = Bundle()

            bundle.putString("jsonValue", jsonValue)
            fragment.arguments = bundle
            return fragment
        }
    }


}