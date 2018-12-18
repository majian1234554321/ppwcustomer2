package com.paipaiwei.personal.ui.fragment

import androidx.viewpager.widget.ViewPager
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfootfragment.*

class CollFootFragment : BaseFragment() {


    val type1 = arrayOf<String>("足迹", "收藏")


    override fun initView() {


        iv_back.setOnClickListener { mActivity.finish() }


        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(CollectionFragment())
        mFragments.add(FootPrintFragment())


        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, type1)



        rg.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rb1 -> {
                    mViewPager.currentItem = 0

                }

                R.id.rb2 -> {
                    mViewPager.currentItem = 1

                }

                else -> {
                }
            }
        }


        mViewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                when (p0) {
                    0 -> {
                        rg.check(R.id.rb1)
                    }
                    else -> {
                        rg.check(R.id.rb2)
                    }
                }
            }

        })


    }

    override fun getLayoutRes(): Int = R.layout.collectionfootfragment


}