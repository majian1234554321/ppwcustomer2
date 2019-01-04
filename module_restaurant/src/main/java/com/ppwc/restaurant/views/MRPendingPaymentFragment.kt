package com.ppwc.restaurant.views

import android.os.Bundle
import android.widget.Toast
import com.ppwc.restaurant.R
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.tencent.mm.opensdk.utils.Log
import com.yjhh.common.Constants
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.pay.RxPay
import kotlinx.android.synthetic.main.mrpendingpaymentfragment.*

class MRPendingPaymentFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.mrpendingpaymentfragment

    override fun initView() {

        val price = arguments?.getString("price")


        tv_pay.text = "确认支付（￥${price}）"

        tv_pay.setOnClickListener {
            start(MRPaySuccessFragment())
        }





        tv_pay.setOnClickListener {


            if (rb_alipay.isChecked) {
                "alipay"

                aliPay()

            } else {
                "wxpay"
                wxPay()
            }





//            Toast.makeText(
//                mActivity, if (rb_alipay.isChecked) {
//                    "alipay"
//                } else {
//                    "wxpay"
//                }, Toast.LENGTH_SHORT
//            ).show()
        }

    }


    fun aliPay() {
        val str =
            "partner=\"2088121059329235\"&seller_id=\"1993349866@qq.com\"&out_trade_no=\"XGJ_LIVE20171130142905-440402\"&subject=\"一对一收费单节\"&body=\"一对一收费单节\"&total_fee=\"0.01\"&notify_url=\"http://new.antwk.com/api/order/alipayNotify\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"1757281m\"&return_url=\"m.alipay.com\"&sign=\"vn%2Fw5wJAYSdP5rtQxumnAXPaaidyeVOluEoDlvS4axezmvfpoIHzwxj5pqNrJ5NMKq7NK8krHWBo8Z6jeTkFbCb2mvLbyBicAjDz02WyPOmKM%2F%2FGRfqfDlX4Q0T06PQmipNFVD3UPHrwPQbHG3eeWobqBFG0jcu%2FtnMZrsZvzso%3D\"&sign_type=\"RSA\""
        val disposable = RxPay(mActivity).requestAlipay(str)
            .subscribe({ aBoolean ->
                Log.e("accept", "accept: " + aBoolean!!)
            }) { throwable ->
                Log.e("accept", "accept: " + throwable.toString())
            }
        compositeDisposable.add(disposable)
    }

    fun wxPay() {

       val api = WXAPIFactory.createWXAPI(mActivity, Constants.APP_ID_WX)

        val req = PayReq()

        req.appId = Constants.APP_ID_WX
        req.partnerId = "partnerid"
        req.prepayId = "prepayid"
        req.nonceStr = "noncestr"
        req.timeStamp = "timestamp"
        req.packageValue = "package"
        req.sign = "sign"
        req.extData = "app data" // optional



        api.sendReq(req)



      /*  val str = "{\"prepayId\":\"wx20171130142918877d249e440347896475\"}"
        val disposable = RxPay(mActivity).requestWXpay(str)
            .subscribe({ aBoolean ->
                Log.e("accept:", aBoolean.toString())
            }) { throwable ->
                Log.e("accept: ", throwable.toString())
            }
        compositeDisposable.add(disposable)*/

    }


    companion object {
        fun newInstance(price: String): MRPendingPaymentFragment {
            val fragment = MRPendingPaymentFragment()
            val bundle = Bundle()

            bundle.putString("price", price)
            fragment.arguments = bundle
            return fragment
        }
    }


}
