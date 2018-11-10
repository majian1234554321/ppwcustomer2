package com.yjhh.ppwcustomer.ui.activity.takeout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2ViewPagerAdapter
import com.yjhh.ppwcustomer.ui.activity.coupon.DisenableCouponFragment
import com.yjhh.ppwcustomer.ui.activity.coupon.EnableCouponFragment
import kotlinx.android.synthetic.main.activity_refresh_layout.view.*
import kotlinx.android.synthetic.main.activity_use_coupon.*

class UseCouponActivity : BaseActivity() {
    private val mTitles = arrayOf("可用优惠券", "不可用优惠券")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_coupon)
        segmentTabLayout.setTabData(mTitles)


        val fragments = ArrayList<BaseFragment>()


        fragments.add(EnableCouponFragment())

        fragments.add(DisenableCouponFragment())


        viewpager.adapter = Main2ViewPagerAdapter(supportFragmentManager, fragments, mTitles)






        segmentTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                viewpager.currentItem = position
            }

            override fun onTabReselect(position: Int) {}
        })



        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                segmentTabLayout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }
}
