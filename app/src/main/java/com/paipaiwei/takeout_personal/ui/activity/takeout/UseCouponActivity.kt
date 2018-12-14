package com.paipaiwei.takeout_personal.ui.activity.takeout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.Main2ViewPagerAdapter
import com.paipaiwei.takeout_personal.ui.activity.coupon.DisenableCouponFragment
import com.paipaiwei.takeout_personal.ui.activity.coupon.EnableCouponFragment
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



        viewpager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
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
