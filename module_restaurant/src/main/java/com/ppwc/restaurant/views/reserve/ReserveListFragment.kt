package com.ppwc.restaurant.views.reserve

import android.os.Bundle
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.base.TABViewpagetAdapter
import kotlinx.android.synthetic.main.reservelistfragment.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment
import java.util.ArrayList

class ReserveListFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reservelistfragment

    override fun initView() {

        val mTitles = arrayOf("当前预约", "历史预约");

        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(ReserveList1Fragment())
        mFragments.add(ReserveList2Fragment())

        ImmersionBar.setTitleBar(mActivity, tbv_title)

        mViewPager.adapter = TABViewpagetAdapter(childFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)

    }

    fun startBrotherFragment(targetFragment: SupportFragment) {
        start(targetFragment)
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle) {
        super.onFragmentResult(requestCode, resultCode, data)
//        if (resultCode == ISupportFragment.RESULT_OK) {
//            val value = data?.getString("value")
//
//            if (value != null) {
//                Toast.makeText(_mActivity, value+"XX", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}