package com.ppwc.restaurant.views

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ppwc.restaurant.R
import com.ppwc.restaurant.mrlistener.AlphaPageTransformer
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.mrcheckpayadapter.*
import kotlinx.android.synthetic.main.mrcheckpayfragment.*
import kotlinx.android.synthetic.main.mrpaysuccessfragment.*
import net.cachapa.expandablelayout.ExpandableLayout
import org.w3c.dom.Text


class MRCheckPayFragment : BaseFragment() {


    var tv1price = "0"
    var tv2price = "0"
    var tv3price = "0"
    var tv4price = "0"


    var tv_useIf: TextView? = null


    var view22:View? = null

    override fun getLayoutRes(): Int = R.layout.mrcheckpayfragment

    override fun initView() {
        mb_buy.setOnClickListener {

            if ("0" != tv1price) {
                val dialog = ProofreadingDialogFragment(tv1price, tv2price, tv3price, tv4price)
                dialog.show(childFragmentManager, "TAG")

                dialog.setOnDialogClick(object : ProofreadingDialogFragment.OnDialogClickListener {
                    override fun onDialogClick() {

                        start(MRPendingPaymentFragment.newInstance(tv4price))
                        dialog.dismiss()
                    }

                })
            } else {
                Toast.makeText(mActivity, "实付金额必须大于0", Toast.LENGTH_SHORT).show()
            }


        }



        mViewPager.pageMargin = 40
      //  mViewPager.setPadding(40,0,40,0)

        mViewPager.offscreenPageLimit = 3
        mViewPager.adapter = object : PagerAdapter() {
            override fun getCount(): Int = 3


            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                view22 = View.inflate(mActivity, R.layout.mrcheckpayadapter, null)

                val rl_head = view22?.findViewById<RelativeLayout>(R.id.rl_head)

                val tv_price = view22?.findViewById<TextView>(R.id.tv_price)
                val tv_access = view22?.findViewById<TextView>(R.id.tv_access)
                 tv_useIf = view22?.findViewById<TextView>(R.id.tv_useIf)
                val tv_cardName = view22?.findViewById<TextView>(R.id.tv_cardName)

               val rl_footer = view22?.findViewById<RelativeLayout>(R.id.rl_footer)


                rl_head?.setBackgroundResource(R.drawable.iv_red)


                view22?.setOnClickListener {
                    Toast.makeText(mActivity,"view",Toast.LENGTH_SHORT).show()

                    rl_footer?.visibility= View.VISIBLE
                }

                tv_useIf?.setOnClickListener {
                    Toast.makeText(mActivity,"tv_useIf",Toast.LENGTH_SHORT).show()
                    rl_footer?.visibility= View.GONE
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




        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override   fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {


            }

            override fun onPageScrollStateChanged(state: Int) {

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
                    tv4price = calculation(et_totleprice.text.toString(), it.toString(), "1", "0").toString()
                    tv_finalprice.text =
                            calculation(et_totleprice.text.toString(), it.toString(), "1", "0").toString()
                } else {
                    tv_finalprice.text = ""

                    tv1price = "0"
                    tv4price = "0"
                }


            } else {
                if (!TextUtils.isEmpty(et_totleprice.text.toString().trim())) {
                    tv_finalprice.text =
                            calculation(et_totleprice.text.toString(), "0", "1", "0").toString()

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