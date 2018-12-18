package com.paipaiwei.personal.ui.fragment

import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class CollectionFragment:BaseFragment() {


    val array =  arrayOf("商家","商品")

    override fun getLayoutRes(): Int  = R.layout.collectionfragment


    override fun initView() {


        val  mFragments = ArrayList<BaseFragment>()
        mFragments.add(Collection1Fragment())
        mFragments.add(Collection2Fragment())


        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)




    }
}