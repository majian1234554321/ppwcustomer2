package com.paipaiwei.takeout_personal.ui.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.paipaiwei.takeout_personal.R
import android.view.ViewTreeObserver
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.yjhh.common.base.BaseActivity
import com.paipaiwei.takeout_personal.adapter.GuideViewPagerAdapter
import com.yjhh.common.utils.ActivityCollector
import kotlinx.android.synthetic.main.activity_guide.*

@Route(path = "/guideactivity/guide")
class GuideActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        val mViewList = ArrayList<View>()
        val view1 = ImageView(this)
        view1.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(this).load(R.mipmap.indicator1).into(view1)

        val view2 = ImageView(this)
        view2.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(this).load(R.mipmap.indicator2).into(view2)

        val view3 = ImageView(this)
        view3.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(this).load(R.mipmap.indicator3).into(view3)

        mViewList.add(view1)
        mViewList.add(view2)
        mViewList.add(view3)

        addDots()
        moveDots()

        in_viewpager.adapter = GuideViewPagerAdapter(mViewList)

        in_viewpager.currentItem = 0

        bt_next.setOnClickListener {
            ActivityCollector.JumpActivity(this@GuideActivity, MainActivity::class.java)
            finish()
        }

    }

    var mDistance: Int = 0

    private fun moveDots() {
        iv_light_dots.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                mDistance = in_ll.getChildAt(1).left - in_ll.getChildAt(0).left
                iv_light_dots.viewTreeObserver
                    .removeOnGlobalLayoutListener(this)
            }
        })
        in_viewpager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                val leftMargin = mDistance * (position + positionOffset)
                val params = iv_light_dots.layoutParams as RelativeLayout.LayoutParams
                params.leftMargin = leftMargin.toInt()
                iv_light_dots.layoutParams = params
                if (position == 2) {
                    bt_next.visibility = View.VISIBLE
                }
                if (position != 2 && bt_next.visibility == View.VISIBLE) {
                    bt_next.visibility = View.GONE
                }
            }

            override fun onPageSelected(position: Int) {

               // val leftMargin = mDistance * position
                val params = iv_light_dots.layoutParams as RelativeLayout.LayoutParams
                //params.leftMargin = leftMargin
                iv_light_dots.layoutParams = params
                if (position == 2) {
                    bt_next.visibility = View.VISIBLE
                }
                if (position != 2 && bt_next.visibility == View.VISIBLE) {
                    bt_next.visibility = View.GONE

                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    private lateinit var mOne_dot: ImageView
    private lateinit var mTwo_dot: ImageView
    private lateinit var mThree_dot: ImageView

    private fun addDots() {
        mOne_dot = ImageView(this);
        mOne_dot.setImageResource(R.drawable.gray_dot)
        val layoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 40, 0)
        in_ll.addView(mOne_dot, layoutParams)
        mTwo_dot = ImageView(this)
        mTwo_dot.setImageResource(R.drawable.gray_dot)
        in_ll.addView(mTwo_dot, layoutParams)
        mThree_dot = ImageView(this)
        mThree_dot.setImageResource(R.drawable.gray_dot)
        in_ll.addView(mThree_dot, layoutParams)
        setClickListener()

    }

    private fun setClickListener() {
        mOne_dot.setOnClickListener { in_viewpager.currentItem = 0 }
        mTwo_dot.setOnClickListener { in_viewpager.currentItem = 1 }
        mThree_dot.setOnClickListener { in_viewpager.currentItem = 2 }
    }
}
