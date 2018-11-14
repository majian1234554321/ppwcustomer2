package com.yjhh.ppwcustomer.ui.fragment

import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class FootPrintFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.collectionfragment

    val array =  arrayOf("商家","商品","文章")


    override fun initView() {


        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(FootPrint1Fragment())
        mFragments.add(FootPrint2Fragment())
        mFragments.add(FootPrint3Fragment())

        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)


    }

}
