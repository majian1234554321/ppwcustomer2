package com.paipaiwei.personal.ui.fragment

import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class FootPrintFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.collectionfragment

    val array =  arrayOf("商家","商品")


    override fun initView() {


        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(FootPrint1Fragment())
        mFragments.add(FootPrint2Fragment())

        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)


    }

}
