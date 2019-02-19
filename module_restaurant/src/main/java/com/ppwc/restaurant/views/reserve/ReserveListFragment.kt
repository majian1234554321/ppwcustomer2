package com.ppwc.restaurant.views.reserve

import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.base.TABViewpagetAdapter
import kotlinx.android.synthetic.main.reservelistfragment.*
import java.util.ArrayList

class ReserveListFragment :BaseFragment() {
    override fun getLayoutRes(): Int  = R.layout.reservelistfragment

    override fun initView() {

        val mTitles = arrayOf("当前预约","历史预约");


        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(ReserveList1Fragment())
        mFragments.add(ReserveList2Fragment())

        ImmersionBar.setTitleBar(mActivity,tbv_title)

        mViewPager.adapter = TABViewpagetAdapter(childFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)

    }
}