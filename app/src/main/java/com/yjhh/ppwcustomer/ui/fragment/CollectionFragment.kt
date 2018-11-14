package com.yjhh.ppwcustomer.ui.fragment

import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class CollectionFragment:BaseFragment() {


    val array =  arrayOf("商家","商品","文章")

    override fun getLayoutRes(): Int  = R.layout.collectionfragment


    override fun initView() {


        val  mFragments = ArrayList<BaseFragment>()
        mFragments.add(Collection1Fragment())
        mFragments.add(Collection2Fragment())
        mFragments.add(Collection3Fragment())

        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)




    }
}