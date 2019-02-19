package com.ppwc.restaurant.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import android.view.View
import android.widget.TextView
import android.widget.Toast
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

import kotlin.collections.ArrayList

class ParishReserveFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.activity_parish_reserve


    var selectData = DateUtil.getFetureDate(0, "YMD")

    override fun initView() {


        val shopId = arguments?.getString("shopId")
        val shopName = arguments?.getString("shopName")

        ImmersionBar.setTitleBar(mActivity, tbv_title)

        tbv_title.title = shopName

        val city = resources.getStringArray(R.array.arrays_time)


        val dataset = city.asList()
        nice_spinner.attachDataSource(dataset)
        nice_spinner.text = city[10]


        val dateList = ArrayList<DateBean>()

        for (i in 0 until 15) {
            val bean = DateBean(
                DateUtil.dayForWeek(DateUtil.getFetureDate(i, "YMD")), DateUtil.getFetureDate(i, "YMD"),
                DateUtil.getFetureDate(i, "MD"),
                TimeUtil.dateToStamp(DateUtil.getFetureDate(i, "YMD"))
            )

            dateList.add(bean)
            val view = View.inflate(mActivity, R.layout.date15adapter, null)
            view.findViewById<TextView>(R.id.tv_MD).text = bean.MMDD
            view.findViewById<TextView>(R.id.tv_week).text = bean.week

            mTabLayout.addTab(mTabLayout.newTab())

            mTabLayout.getTabAt(i)?.customView = view
        }

        mTabLayout.setSelectedTabIndicator(0)
        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {


                if (p0 != null) {
                    selectData = dateList.get(p0.position).YYMMDD
                }
            }

        })


        tv_commit.setOnClickListener {

            val model = SubmitReservationModel()


            // TimeUtil.stampToDate(selectData.plus(" ").plus(nice_spinner.text))

            model.time = TimeUtil.dateToStamp2("$selectData ${nice_spinner.text.toString()}:00")

            model.mobile = et_phone?.text.toString().trim()
            model.remark = et_remark?.text.toString().trim()
            model.shopId = shopId
            model.name = et_Contacts.text.toString().trim()
            model.count = "1"


            ApiServices.getInstance().create(ReserveService::class.java)
                .submitReservation(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ProcessObserver2(mActivity) {
                    override fun processValue(response: String?) {
                        Log.i("ParishReserveActivity", response)

                        start(ReserveDetailFragment())
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
