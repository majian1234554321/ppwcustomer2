package com.paipaiwei.personal.ui.activity.pai

import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter

import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.paifragment.*

class PaiFragment : BaseFragment() {
    override fun getLayoutRes() = R.layout.paifragment

    val array = arrayOf("今日抢拍", "明日抢购")

    override fun initView() {
        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(Pai1Fragment())
        mFragments.add(Pai2Fragment())
        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)
    }

}