package com.paipaiwei.personal.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.jakewharton.rxbinding2.view.RxView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.ui.activity.onepay.PayResultFragment
import com.paipaiwei.personal.ui.activity.onepay.PayResultFragment2

import com.paipaiwei.personal.view.OrderView
import com.ppwc.restaurant.views.OrderEvaluationFragment
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.DisplayPayTypeBean
import com.yjhh.common.iview.PayView
import com.yjhh.common.model.WxPayBean
import com.yjhh.common.pay.RxPay
import com.yjhh.common.present.PayPresent
import com.yjhh.common.utils.APKVersionCodeUtils
import com.yjhh.common.utils.DateUtil
import com.yjhh.common.utils.RxCountDown
import kotlinx.android.synthetic.main.payfragment.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class PayFragment : BaseFragment(), PayView, OrderView {


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
                        toDispatchSuccess(model.id, "1")
                    } else {
                        toDispatchError(model.id, "1")

                    }
                }) { throwable ->

                    Log.e("accept:WX ", throwable.toString())
                }
            compositeDisposable.add(disposable)


        }
    }

    override fun onAliPayValue(value: String?) {
        if (value != null) {

            val jsonObject = JSONObject(value)
            val str = jsonObject.optString("data")
            val id = jsonObject.optString("id")
//            val str =
//                "partner=\"2088121059329235\"&seller_id=\"1993349866@qq.com\"&out_trade_no=\"XGJ_LIVE20171130142905-440402\"&subject=\"一对一收费单节\"&body=\"一对一收费单节\"&total_fee=\"0.01\"&notify_url=\"http://new.antwk.com/api/order/alipayNotify\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"1757281m\"&return_url=\"m.alipay.com\"&sign=\"vn%2Fw5wJAYSdP5rtQxumnAXPaaidyeVOluEoDlvS4axezmvfpoIHzwxj5pqNrJ5NMKq7NK8krHWBo8Z6jeTkFbCb2mvLbyBicAjDz02WyPOmKM%2F%2FGRfqfDlX4Q0T06PQmipNFVD3UPHrwPQbHG3eeWobqBFG0jcu%2FtnMZrsZvzso%3D\"&sign_type=\"RSA\""
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

    override fun onFault(errorMsg: String?) {

    }

    override fun onSuccessOrder(response: String?, flag: String?) {

    }

    var payPresent: PayPresent? = null
    var orderPresent: OrderPresent? = null
    var type: String? = null

    override fun getLayoutRes(): Int = R.layout.payfragment

    override fun initView() {

        ImmersionBar.setTitleBar(mActivity, tbv_title)

        val jsonValue = arguments?.getString("jsonValue")
        type = arguments?.getString("type")



        payPresent = PayPresent(mActivity, this)

        orderPresent = OrderPresent(mActivity, this)

        val gson = Gson()
        val model = gson.fromJson<DisplayPayTypeBean>(jsonValue, DisplayPayTypeBean::class.java)



        model_info.text = model.title
        model_price.text = mActivity.getString(R.string.rmb_price_double2, model.money)

        val dis5 = RxCountDown.countdown(900).subscribe {
            if (it != null && tv_countdown != null)
                tv_countdown.text = DateUtil.getFormatDHMmDate(it)
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
                    payPresent?.paymentByAli(model.id, model.money.toInt().toString())

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

    companion object {
        fun newInstance(jsonValue: String?, type: String?): PayFragment {
            val fragment = PayFragment()
            val bundle = Bundle()

            bundle.putString("jsonValue", jsonValue)
            bundle.putString("type", type)




            fragment.arguments = bundle
            return fragment
        }
    }


    fun toDispatchSuccess(id: String?, type: String?) {
        when (type) {
            "限时抢拍" -> {
                startWithPop(PayResultFragment.newInstance(id, type)) //	//1微信 2支付宝 4银联
            }
            "道具购买" -> {
                startWithPop(PayResultFragment.newInstance(id, type)) //	//1微信 2支付宝 4银联
            }
            "商家买单" -> {
                startWithPop(OrderEvaluationFragment())
            }

            else -> {
                startWithPop(PayResultFragment.newInstance(id, type))
            }
        }
    }

    fun toDispatchError(id: String?, type: String?) {
        startWithPop(PayResultFragment.newInstance(id, type)) //	//1微信 2支付宝 4银联
        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show()
    }


}