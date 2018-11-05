package com.yjhh.ppwcustomer.ui.fragment

import android.view.View
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.membershipcardfragment.*
import com.yjhh.ppwcustomer.R.id.mViewPager
import com.yjhh.ppwcustomer.common.ScaleTransformer
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwcustomer.adapter.MembershipCardAdapter
import com.yjhh.ppwcustomer.adapter.MyVpAdater


class MembershipCardFragment :BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.membershipcardfragment

    override fun initView() {





        val list = ArrayList<Int>()
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        list.add(R.mipmap.timg)
        val adater = MyVpAdater(context, list)

        mViewPager.offscreenPageLimit = list.size

        mViewPager.adapter = adater


        mViewPager.setPageTransformer(true, ScaleTransformer())



        val  list2  = ArrayList<String>()

        list2.add("1")
        list2.add("1")
        list2.add("1")
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mRecyclerView.adapter = MembershipCardAdapter(context,list2)

    }
}