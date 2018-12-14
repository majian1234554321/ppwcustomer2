package com.paipaiwei.takeout_personal.ui.fragment

import com.yjhh.common.base.BaseFragment

import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.MyPagerAdapter
import com.paipaiwei.takeout_personal.ui.fragment.MessageCenter1Fragment
import com.paipaiwei.takeout_personal.ui.fragment.MessageCenter2Fragment
import kotlinx.android.synthetic.main.messagecenterfragment.*

class MessageCenterFragment : BaseFragment() {

    val array = arrayOf("我的消息", "系统消息")
    override fun getLayoutRes(): Int = R.layout.messagecenterfragment

    override fun initView() {
        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(MessageCenter1Fragment())
        mFragments.add(MessageCenter2Fragment())


        mViewPager.adapter = MyPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)
    }
}
