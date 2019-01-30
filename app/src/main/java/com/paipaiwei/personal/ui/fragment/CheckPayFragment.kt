package com.paipaiwei.personal.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.jakewharton.rxbinding2.widget.RxTextView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.ui.activity.PayActivity
import com.paipaiwei.personal.ui.activity.onepay.OnePayMoneyFragment
import com.ppwc.restaurant.bean.ShopPayPageInitModel
import com.ppwc.restaurant.bean.SubmitShopPayModel
import com.ppwc.restaurant.iview.PrePayCheckServie
import com.ppwc.restaurant.mrlistener.AlphaPageTransformer
import com.ppwc.restaurant.views.MRCheckPayFragment
import com.ppwc.restaurant.views.MRPendingPaymentFragment
import com.ppwc.restaurant.views.ProofreadingDialogFragment
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.TextStyleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.checkpayfragment.*
import java.lang.StringBuilder
import java.text.DecimalFormat

class CheckPayFragment : BaseFragment() {


    var tv1price = "0"
    var tv2price = "0"
    var tv3price = "0"
    var tv4price = "0"


    var tv_useIf: TextView? = null
    var shopId: String? = null

    var view22: View? = null


    override fun getLayoutRes(): Int = R.layout.checkpayfragment

    override fun initView() {
        val couponId = arguments?.getString("couponId")
        val orderId = arguments?.getString("orderId")
        val typeId = arguments?.getString("typeId")
        shopId = arguments?.getString("shopId")

        mb_buy.setOnClickListener {

            if ("0" != tv1price) {
                val dialog = ProofreadingDialogFragment(tv1price, tv2price, tv3price, tv4price)
                dialog.show(childFragmentManager, "TAG")

                dialog.setOnDialogClick(object : ProofreadingDialogFragment.OnDialogClickListener {
                    override fun onDialogClick() {

                        val model2 = SubmitShopPayModel()

                        model2.couponId = ""  //（页面A）使用的券id
                        model2.type = typeId //来源 1、商家首页 2卡券中心 3订单列表
                        model2.money = tv1price //总消费金额
                        model2.orderId = "" //（页面B）订单id
                        model2.result = tv4price //客户端计算的结果（参数校验）
                        model2.shopId = shopId //消费门店
                        model2.unDisMoney = tv2price //不参与优惠金额


                        ApiServices.getInstance().create(PrePayCheckServie::class.java).paySubmit(model2)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : ProcessObserver2(mActivity) {
                                override fun processValue(response: String?) {
                                    Log.i("paySubmit", response)


                                    ARouter.getInstance()
                                        .build("/PayActivity/pay")
                                        .withString("jsonValue", response)
                                        .withString("type", "商家买单")
                                        .navigation()
                                    dialog.dismiss()

                                }

                                override fun onFault(message: String) {
                                    Log.i("paySubmit", message)
                                }

                            })




                        dialog.dismiss()
                    }

                })
            } else {
                Toast.makeText(mActivity, "实付金额必须大于0", Toast.LENGTH_SHORT).show()
            }


        }

        mViewPager.pageMargin = 40



        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {


            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


        val model = ShopPayPageInitModel()

        model.couponId = couponId //卡券id
        model.orderId = orderId //订单id
        model.shopId = shopId //订单id
        model.type = typeId
        //type
        /*  1、商家首页，立即买单（页面A）
          2、卡券中心，我要使用（页面A）
          3、订单列表，立即使用（页面B）
          4、待定
          5、待定
          其他视为非法请求*/

        ApiServices.getInstance().create(PrePayCheckServie::class.java).pay(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("ShopPayPageInitModel", response)

                    val mrBean = Gson().fromJson<MRCheckPayFragment.MRCheckPayBean>(
                        response,
                        MRCheckPayFragment.MRCheckPayBean::class.java
                    )

                    if (!(mrBean?.coupons == null || !mrBean.coupons.isNotEmpty())) {


                        mViewPager.offscreenPageLimit = mrBean?.coupons.size
                        mViewPager.adapter = object : PagerAdapter() {
                            override fun getCount(): Int = mrBean?.coupons.size


                            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                                view22 = View.inflate(mActivity, com.ppwc.restaurant.R.layout.mrcheckpayadapter, null)

                                val rl_head = view22?.findViewById<RelativeLayout>(com.ppwc.restaurant.R.id.rl_head)

                                val tv_price = view22?.findViewById<TextView>(com.ppwc.restaurant.R.id.tv_price)
                                val tv_mark = view22?.findViewById<TextView>(com.ppwc.restaurant.R.id.tv_mark)


                                val tv_access = view22?.findViewById<TextView>(com.ppwc.restaurant.R.id.tv_access)
                                tv_access?.text = mrBean?.coupons.get(position).useMark
                                tv_useIf = view22?.findViewById<TextView>(com.ppwc.restaurant.R.id.tv_useIf)
                                val tv_cardName = view22?.findViewById<TextView>(com.ppwc.restaurant.R.id.tv_cardName)
                                tv_cardName?.text = mrBean?.coupons.get(position).code
                                val rl_footer = view22?.findViewById<RelativeLayout>(com.ppwc.restaurant.R.id.rl_footer)


                                val sbMark = StringBuilder();
                                mrBean?.coupons[position].useMarks.forEach {
                                    sbMark.append(it)
                                    sbMark.append("\n")
                                }

                                tv_mark?.text = sbMark.toString()

                                val tv_priceText =
                                    "${mrBean?.coupons.get(position).value} ${mrBean?.coupons.get(position).valueUnit}"
                                tv_price?.text = TextStyleUtils.changeTextAa(
                                    tv_priceText,
                                    tv_priceText.length - 3,
                                    tv_priceText.length,
                                    15
                                )

                                rl_head?.setBackgroundResource(com.ppwc.restaurant.R.drawable.iv_red)





                                view22?.setOnClickListener {


                                    rl_footer?.visibility = View.VISIBLE
                                }

                                tv_useIf?.setOnClickListener {

                                    if (rl_footer?.visibility == View.VISIBLE) {
                                        rl_footer?.visibility = View.GONE
                                    } else {
                                        rl_footer?.visibility == View.VISIBLE
                                    }


                                }

                                container.addView(view22)


                                return (view22 as View?)!!
                            }

                            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                                container.removeView(`object` as View)
                            }

                            override fun isViewFromObject(view: View, o: Any): Boolean {
                                return view === o
                            }
                        }
                        mViewPager.setPageTransformer(true, AlphaPageTransformer())


                    } else {
                        mViewPager.visibility = View.GONE
                        rl1.visibility = View.GONE
                        rl_3.visibility = View.GONE
                        tv_count_tips.visibility = View.VISIBLE
                    }

                    tv_select.text = mrBean.shopName

                }

                override fun onFault(message: String) {

                }

            })




        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            discountNoPrice.visibility = if (isChecked) View.VISIBLE else View.GONE
            view_line.visibility = if (isChecked) View.VISIBLE else View.GONE

        }


        val dis = RxTextView.textChanges(et_totleprice).subscribe {

            if (!TextUtils.isEmpty(it)) {

                tv1price = it.toString()
                if (!TextUtils.isEmpty(discountNoPrice.text.toString().trim())) {

                    tv2price = discountNoPrice.text.toString().trim()
                    tv_finalprice.text =
                        calculation(it.toString(), discountNoPrice.text.toString(), "1", "0").toString()
                    tv4price = tv_finalprice.text.toString()

                } else {
                    tv2price = "0"
                    tv_finalprice.text = calculation(it.toString(), "0", "1", "0").toString()

                    tv4price = tv_finalprice.text.toString()
                }


            } else {
                tv1price = "0"
                tv2price = "0"
                tv3price = "0"
                tv_finalprice.text = ""
                tv4price = "0"
            }

        }


        val dis2 = RxTextView.textChanges(discountNoPrice).subscribe {
            if (!TextUtils.isEmpty(it)) {
                tv2price = it.toString()

                if (!TextUtils.isEmpty(et_totleprice.text.toString().trim())) {
                    tv1price = et_totleprice.text.toString()
                    tv4price = calculation(et_totleprice.text.toString(), it.toString(), "1", "0")
                    tv_finalprice.text =
                        calculation(et_totleprice.text.toString(), it.toString(), "1", "0")
                } else {
                    tv_finalprice.text = ""
                    tv1price = "0"
                    tv4price = "0"
                }


            } else {
                if (!TextUtils.isEmpty(et_totleprice.text.toString().trim())) {
                    tv_finalprice.text =
                        calculation(et_totleprice.text.toString(), "0", "1", "0")

                    tv1price = et_totleprice.text.toString()
                    tv2price = "0"
                    tv4price = tv_finalprice.text.toString()
                } else {
                    tv_finalprice.text = ""
                    tv1price = "0"
                    tv2price = "0"
                    tv3price = "0"
                    tv4price = "0"
                }
            }
        }


        compositeDisposable.add(dis)
        compositeDisposable.add(dis2)
    }


    fun calculation(totleprice: String, discountNoPrice: String, flag: String?, discountValue: String): String {

        val df = DecimalFormat("#0.00")
        var price = 0f

        price = if ("1" == flag) { //  类型（0满减（面值）1 抵扣（折扣百分比））
            if ("0" == discountValue) {  //“0” 不打折扣
                (totleprice.toFloat() - discountNoPrice.toFloat())
            } else {
                (totleprice.toFloat() - discountNoPrice.toFloat()) * discountValue.toFloat() +
                        discountNoPrice.toFloat()
            }

        } else {
            totleprice.toFloat() - discountNoPrice.toFloat() - discountValue.toFloat() +
                    discountNoPrice.toFloat()
        }


        return getString(R.string.rmb_price_double, price)
    }


    companion object {
        fun newInstance(couponId: String?, orderId: String?, shopId: String?, typeId: String?): CheckPayFragment {
            val fragment = CheckPayFragment()
            val bundle = Bundle()

            bundle.putString("couponId", couponId)
            bundle.putString("orderId", orderId)
            bundle.putString("shopId", shopId)
            bundle.putString("typeId", typeId)



            fragment.arguments = bundle
            return fragment
        }
    }
//
//
//    data class MRCheckPayBean(
//        val coupons: List<Any>,
//        val shopId: Int,
//        val shopName: String,
//        val type: Int
//    )

}