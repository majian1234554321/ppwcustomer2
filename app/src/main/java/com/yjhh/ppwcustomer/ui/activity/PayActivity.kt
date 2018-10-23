package com.yjhh.ppwcustomer.ui.activity


import android.os.Bundle
import com.tencent.mm.opensdk.utils.Log
import com.yjhh.common.base.BaseActivity
import com.yjhh.ppwcustomer.R

import com.yjhh.ppwcustomer.pay.RxPay

class PayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        alipay()
        wechatPay()
    }


    fun alipay() {
        //服务器产生的订单信息
        val str =
            "partner=\"2088121059329235\"&seller_id=\"1993349866@qq.com\"&out_trade_no=\"XGJ_LIVE20171130142905-440402\"&subject=\"一对一收费单节\"&body=\"一对一收费单节\"&total_fee=\"0.01\"&notify_url=\"http://new.antwk.com/api/order/alipayNotify\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"1757281m\"&return_url=\"m.alipay.com\"&sign=\"vn%2Fw5wJAYSdP5rtQxumnAXPaaidyeVOluEoDlvS4axezmvfpoIHzwxj5pqNrJ5NMKq7NK8krHWBo8Z6jeTkFbCb2mvLbyBicAjDz02WyPOmKM%2F%2FGRfqfDlX4Q0T06PQmipNFVD3UPHrwPQbHG3eeWobqBFG0jcu%2FtnMZrsZvzso%3D\"&sign_type=\"RSA\""
        val disposable = RxPay(this).requestAlipay(str)
            .subscribe({ aBoolean ->
                Log.e("oye", "accept: " + aBoolean!!)
            }) { throwable ->
                Log.e("oye", "accept: " + throwable.toString())
            }
        compositeDisposable.add(disposable)

    }

    fun wechatPay() {
        //"服务器生成订单后的json 具体看README格式"
        val str = "{\"prepayId\":\"wx20171130142918877d249e440347896475\"}"
        val disposable = RxPay(this).requestWXpay(str)
            .subscribe({ aBoolean ->
                Log.e("accept:", aBoolean.toString())
            }) { throwable ->
                Log.e("accept: ", throwable.toString())
            }
        compositeDisposable.add(disposable)
    }


}
