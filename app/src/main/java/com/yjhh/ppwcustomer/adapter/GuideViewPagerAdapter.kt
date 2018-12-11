package com.yjhh.ppwcustomer.adapter

import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.view.ViewGroup


class GuideViewPagerAdapter(private val mViewList: List<View>) : androidx.viewpager.widget.PagerAdapter() {

    override fun getCount(): Int {
        return mViewList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mViewList[position])
        return mViewList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mViewList[position])
    }
}