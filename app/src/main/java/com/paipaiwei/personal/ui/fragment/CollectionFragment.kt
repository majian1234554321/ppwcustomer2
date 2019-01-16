package com.paipaiwei.personal.ui.fragment

import android.os.Bundle
import com.flyco.tablayout.listener.CustomTabEntity
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.collectionfragment.*

class CollectionFragment : BaseFragment() {


    val array = arrayOf("商家", "商品")

    override fun getLayoutRes(): Int = R.layout.collectionfragment


    override fun initView() {


        val type = arguments?.getString("type")

        tbv_title.setTitle("我的$type")

        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(Collection1Fragment.newInstance(type))
        mFragments.add(Collection2Fragment.newInstance(type))

        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)

    }


    companion object {
        fun newInstance(type: String?): CollectionFragment {
            val fragment = CollectionFragment()
            val bundle = Bundle()

            bundle.putString("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }


}