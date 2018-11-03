package com.yjhh.ppwcustomer.ui.fragment


import android.view.View
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R

import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter

import com.yjhh.ppwcustomer.bean.TabEntity
import kotlinx.android.synthetic.main.main2fragment.*
import kotlinx.android.synthetic.main.main2fragment.view.*
import java.util.ArrayList


class Main2Fragment : BaseFragment() {
    override fun initView(rootView: View?) {
        val mTitles = arrayOf("全部", "进行中", "待评价")




        val mTabEntities = ArrayList<CustomTabEntity>()
        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(Main2_1Fragment())
        mFragments.add(Main2_2Fragment())
        mFragments.add(Main2_3Fragment())



        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments,mTitles)
        mTabLayout.setViewPager(mViewPager)

    }

    override fun getLayoutRes(): Int = R.layout.main2fragment
}