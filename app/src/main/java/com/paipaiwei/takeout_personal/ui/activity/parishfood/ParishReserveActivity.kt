package com.paipaiwei.takeout_personal.ui.activity.parishfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.bean.DateBean
import com.paipaiwei.takeout_personal.common.utils.DateUtil
import com.paipaiwei.takeout_personal.common.utils.TimeUtil
import kotlinx.android.synthetic.main.activity_parish_reserve.*
import java.util.*
import kotlin.collections.ArrayList

class ParishReserveActivity : AppCompatActivity() {
    var selectData = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parish_reserve)


        val city = resources.getStringArray(R.array.arrays_time)


        val dataset = city.asList()
        nice_spinner.attachDataSource(dataset)
        nice_spinner.text = city[10]


        var dateList = ArrayList<DateBean>()

        for (i in 0 until 15) {
            val bean = DateBean()
            bean.YYMMDD = DateUtil.getFetureDate(i, "YMD")
            bean.week = DateUtil.dayForWeek(DateUtil.getFetureDate(i, "YMD"))
            bean.MMDD = DateUtil.getFetureDate(i, "MD")
            bean.timeStamp = TimeUtil.dateToStamp(DateUtil.getFetureDate(i, "YMD"))
            dateList.add(bean)
            val view = View.inflate(this, R.layout.date15adapter, null)
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
                selectData = p0?.position!!
            }

        })


    }
}
