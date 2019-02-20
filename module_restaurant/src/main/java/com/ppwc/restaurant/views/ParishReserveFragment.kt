package com.ppwc.restaurant.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import com.gyf.barlibrary.ImmersionBar

import com.yjhh.common.utils.DateUtil

import com.ppwc.restaurant.R
import com.ppwc.restaurant.views.reserve.ReserveDetailFragment
import com.ppwc.restaurant.views.reserve.ReserveService
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.SubmitReservationModel
import com.yjhh.common.utils.TimeUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_parish_reserve.*
import org.json.JSONObject

import kotlin.collections.ArrayList

class ParishReserveFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.activity_parish_reserve


    var selectData = DateUtil.getFetureDate(0, "YMD")

    override fun initView() {


        val shopId = arguments?.getString("shopId")
        val shopName = arguments?.getString("shopName")

        ImmersionBar.setTitleBar(mActivity, tbv_title)

        tbv_title.setTitleValue(shopName)

        val city = resources.getStringArray(R.array.arrays_time)


        val dataset = city.asList()
        nice_spinner.attachDataSource(dataset)
        nice_spinner.text = city[34]


        val dateList = ArrayList<DateBean>()

        for (i in 0 until 15) {
            val bean = DateBean(
                DateUtil.dayForWeek(DateUtil.getFetureDate(i, "YMD")), DateUtil.getFetureDate(i, "YMD"),
                DateUtil.getFetureDate(i, "MD"),
                TimeUtil.dateToStamp(DateUtil.getFetureDate(i, "YMD"))
            )

            dateList.add(bean)
            val view = View.inflate(mActivity, R.layout.date15adapter, null)
            val text1 = view.findViewById<TextView>(R.id.tv_MD)
            text1.text = bean.MMDD
            text1.setTextColor(Color.WHITE)
            val text2 = view.findViewById<TextView>(R.id.tv_week)
            text2.text = bean.week
            text2.setTextColor(Color.WHITE)

            mTabLayout.addTab(mTabLayout.newTab())
            mTabLayout.getTabAt(i)?.customView = view

            if (i == 0) {
                mTabLayout.getTabAt(0)?.customView?.setBackgroundResource(R.drawable.bg_button_pressed2)
                text1.setTextColor(Color.WHITE)
                text2.setTextColor(Color.WHITE)
            } else {
                text1.setTextColor(Color.parseColor("#333333"))
                text2.setTextColor(Color.parseColor("#333333"))
            }


            // view .setBackgroundColor(Color.YELLOW)


        }


        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                if (p0 != null) {


                    mTabLayout.getTabAt(p0.position)?.customView?.setBackgroundResource(R.drawable.bg_button_pressed4)
                    mTabLayout.getTabAt(p0.position)?.customView?.findViewById<TextView>(R.id.tv_MD)
                        ?.setTextColor(Color.parseColor("#333333"))
                    mTabLayout.getTabAt(p0.position)?.customView?.findViewById<TextView>(R.id.tv_week)
                        ?.setTextColor(Color.parseColor("#333333"))
                }
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {


                if (p0 != null) {
                    selectData = dateList[p0.position].YYMMDD
                    mTabLayout.getTabAt(p0.position)?.customView?.setBackgroundResource(R.drawable.bg_button_pressed2)
                    mTabLayout.getTabAt(p0.position)?.customView?.findViewById<TextView>(R.id.tv_MD)
                        ?.setTextColor(Color.WHITE)
                    mTabLayout.getTabAt(p0.position)?.customView?.findViewById<TextView>(R.id.tv_week)
                        ?.setTextColor(Color.WHITE)
                }


            }

        })

        rg_gender.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rb_male -> {
                    rb_male.isChecked = true
                    rb_male.setBackgroundResource(R.drawable.mr_check_bg2)
                    rb_male.setTextColor(Color.WHITE)
                    rb_female.setBackgroundResource(R.drawable.mr_check_bg)
                    rb_female.setTextColor(Color.parseColor("#333333"))
                }
                else -> {
                    rb_female.isChecked = true
                    rb_male.setBackgroundResource(R.drawable.mr_check_bg)
                    rb_male.setTextColor(Color.parseColor("#333333"))
                    rb_female.setTextColor(Color.WHITE)
                    rb_female.setBackgroundResource(R.drawable.mr_check_bg2)
                }
            }
        }




        tv_commit.setOnClickListener {

            val model = SubmitReservationModel()


            // TimeUtil.stampToDate(selectData.plus(" ").plus(nice_spinner.text))

            model.time = TimeUtil.dateToStamp2("$selectData ${nice_spinner.text.toString()}:00")

            model.mobile = et_phone?.text.toString().trim()
            model.remark = et_remark?.text.toString().trim()
            model.shopId = shopId
            model.name = et_Contacts.text.toString().trim()
            model.count = arsv2.count.toString()
            model.gender = if (rb_male.isChecked) {
                "0"
            } else {
                "1"
            }

            ApiServices.getInstance().create(ReserveService::class.java)
                .submitReservation(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ProcessObserver2(mActivity) {
                    override fun processValue(response: String?) {
                        Log.i("ParishReserveActivity", response)


                        start(ReserveDetailFragment.newInstance(JSONObject(response).optString("id")))
                    }

                    override fun onFault(message: String) {
                        Log.i("ParishReserveActivity", message)

                        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
                    }

                })


        }


    }

    companion object {
        fun newInstance(shopId: String?, shopName: String?): ParishReserveFragment {

            val fragment = ParishReserveFragment()
            val bundle = Bundle()

            bundle.putString("shopId", shopId)
            bundle.putString("shopName", shopName)
            fragment.arguments = bundle
            return fragment


        }
    }


    data class DateBean(var week: String, var YYMMDD: String, var MMDD: String, var timeStamp: String)
}
