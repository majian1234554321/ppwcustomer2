package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class CollectionFragment : BaseFragment() {


    override fun initView() {
        val mTitles = arrayOf("商家", "商品", "内容")


        val mTabEntities = ArrayList<CustomTabEntity>()
        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(Main2_1Fragment())
        mFragments.add(Main2_2Fragment())
        mFragments.add(Main2_3Fragment())



        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)

    }

    override fun getLayoutRes(): Int = R.layout.collectionfragment


}