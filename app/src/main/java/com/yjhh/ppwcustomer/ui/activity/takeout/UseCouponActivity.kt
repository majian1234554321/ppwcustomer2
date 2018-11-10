package com.yjhh.ppwcustomer.ui.activity.takeout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_use_coupon.*

class UseCouponActivity : AppCompatActivity() {
    private val mTitles = arrayOf("首页", "消息")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_coupon)
        segmentTabLayout.setTabData(mTitles)

    }
}
