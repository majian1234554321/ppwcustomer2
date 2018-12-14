package com.paipaiwei.takeout_personal.ui.fragment


import androidx.viewpager.widget.ViewPager
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import com.flyco.tablayout.SlidingTabLayout
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.takeout_personal.R

import com.flyco.tablayout.listener.CustomTabEntity
import com.paipaiwei.takeout_personal.adapter.ConstellationAdapter
import com.paipaiwei.takeout_personal.adapter.Main2ViewPagerAdapter

import com.paipaiwei.takeout_personal.bean.TabEntity
import kotlinx.android.synthetic.main.custom_layout.*
import kotlinx.android.synthetic.main.main2fragment.*
import kotlinx.android.synthetic.main.main2fragment.view.*
import java.util.*


class Main3Fragment : BaseMainFragment() {

    private val headers = arrayOf("订单")
    private val constellations =
        arrayOf("全部订单", "美食", "外卖", "酒店", "休闲娱乐", "运动健身", "会员服务")
    private val popupViews = ArrayList<View>()
    override fun initView() {
        val mTitles = arrayOf("全部", "进行中", "待评价")


        val constellationView = layoutInflater.inflate(R.layout.custom_layout, null)

        val constellation = constellationView.findViewById<GridView>(R.id.constellation)


        var constellationAdapter = ConstellationAdapter(context, Arrays.asList(*constellations))
        constellation.adapter = constellationAdapter


        constellation.setOnItemClickListener { parent, view, position, id ->
            constellationAdapter.setCheckItem(position)
            dropDownMenu.setTabText(if (position == 0) headers[0] else constellations[position])
            dropDownMenu.closeMenu()
        }

        popupViews.add(constellationView)


        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(Main3_1Fragment())
        mFragments.add(Main3_2Fragment())
        mFragments.add(Main3_3Fragment())


        val contentView = View.inflate(context, R.layout.main2fragmentcontent, null)

        val mTabLayout = contentView.findViewById<SlidingTabLayout>(R.id.mTabLayout)
        val mViewPager = contentView.findViewById<androidx.viewpager.widget.ViewPager>(R.id.mViewPager)


        dropDownMenu.setDropDownMenu(Arrays.asList(*headers), popupViews, contentView)


        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)

    }

    override fun getLayoutRes(): Int = R.layout.main2fragment
}