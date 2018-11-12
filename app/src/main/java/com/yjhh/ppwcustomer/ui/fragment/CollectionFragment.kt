package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class CollectionFragment : BaseFragment() {


    val type1 = arrayListOf<String>("足迹", "收藏")
    val mTitles = arrayOf("商家", "商品", "内容")

    override fun initView() {



        iv_back.setOnClickListener { mActivity.finish() }


        val mTabEntities = ArrayList<CustomTabEntity>()
        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(Collection1Fragment())
        mFragments.add(Collection2Fragment())
        mFragments.add(Collection3Fragment())



        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)


        rg.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rb1 -> {
                    mViewPager.currentItem= 0
                }

                R.id.rb2 -> {
                    mViewPager.currentItem= 0
                }

                else -> {
                }
            }
        }


    }

    override fun getLayoutRes(): Int = R.layout.collectionfragment


}