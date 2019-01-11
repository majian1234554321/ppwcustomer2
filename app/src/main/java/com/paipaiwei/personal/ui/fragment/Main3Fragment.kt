package com.paipaiwei.personal.ui.fragment


import android.view.View
import android.widget.GridView
import com.alibaba.android.arouter.launcher.ARouter
import com.flyco.tablayout.SlidingTabLayout
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R

import com.paipaiwei.personal.adapter.ConstellationAdapter
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import com.paipaiwei.personal.bean.Main1FootBean

import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.view.OrderView
import kotlinx.android.synthetic.main.main3fragment.*
import java.util.*


class Main3Fragment : BaseMainFragment(), OrderView {
    override fun onSuccessOrder(response: String?, flag: String?) {

    }

    override fun onFault(errorMsg: String?) {

    }

    private val headers = arrayOf("全部订单")
    private val constellations =
        arrayOf("全部订单", "美食", "外卖", "酒店", "休闲娱乐", "运动健身", "会员服务")
    private val popupViews = ArrayList<View>()
    override fun initView() {
        val mTitles = arrayOf("全部订单", "已完成", "未完成", "待评价")

        OrderPresent(mActivity, this).orderTypes()


        val constellationView = layoutInflater.inflate(R.layout.custom_layout, null)

        val constellation = constellationView.findViewById<GridView>(R.id.constellation)


        var constellationAdapter = ConstellationAdapter(context, Arrays.asList(*constellations))
        constellation.adapter = constellationAdapter


        constellation.setOnItemClickListener { parent, view, position, id ->
            constellationAdapter.setCheckItem(position)
            dropDownMenu.setTabText(if (position == 0) headers[0] else constellations[position])
            dropDownMenu.closeMenu()
        }

        popupViews.add(constellationView)


        val mFragments = ArrayList<BaseFragment>()

        mFragments.add(Main3_1Fragment())
        mFragments.add(Main3_2Fragment())
        mFragments.add(Main3_3Fragment())
        mFragments.add(Main3_4Fragment())


        val contentView = View.inflate(context, R.layout.main3fragmentcontent, null)

        val mTabLayout = contentView.findViewById<SlidingTabLayout>(R.id.mTabLayout)
        val mViewPager = contentView.findViewById<androidx.viewpager.widget.ViewPager>(R.id.mViewPager)


        dropDownMenu.setDropDownMenu(Arrays.asList(*headers), popupViews, contentView)


        mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, mTitles)
        mTabLayout.setViewPager(mViewPager)

        tv_consumption.setOnClickListener {
            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "ConsumptionLogFragment")
                //.withString("id", (adapter.data[position] as Main1FootBean.ItemsBean).id)
                .navigation()
        }

    }

    override fun getLayoutRes(): Int = R.layout.main3fragment
}