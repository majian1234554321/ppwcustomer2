package com.paipaiwei.personal.ui.fragment


import android.view.View
import android.widget.GridView
import com.alibaba.android.arouter.launcher.ARouter
import com.flyco.tablayout.SlidingTabLayout
import com.google.gson.Gson
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R

import com.paipaiwei.personal.adapter.ConstellationAdapter
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import com.paipaiwei.personal.apis.OrderService
import com.paipaiwei.personal.bean.OrderNavBean

import com.paipaiwei.personal.view.OrderView
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main3fragment.*
import java.util.*


class Main3Fragment : BaseMainFragment(), OrderView {
    override fun onSuccessOrder(response: String?, flag: String?) {

    }

    override fun onFault(errorMsg: String?) {

    }

    private val headers = arrayOf("全部订单")
    val mTitles = arrayOf("全部订单", "已完成", "未完成", "待评价")
    private val popupViews = ArrayList<View>()
    val mFragments = ArrayList<BaseFragment>()
    var m3_1: Main3_1Fragment? = null

    override fun initView() {

        mFragments.clear()

        m3_1 = Main3_1Fragment()

        mFragments.add(m3_1 as Main3_1Fragment)
        mFragments.add(Main3_2Fragment())
        mFragments.add(Main3_3Fragment())
        mFragments.add(Main3_4Fragment())



        loadData()







        rl0.setOnClickListener {
            loadData()
        }


        tv_consumption.setOnClickListener {
            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "ConsumptionLogFragment")
                //.withString("id", (adapter.data[position] as Main1FootBean.ItemsBean).id)
                .navigation()
        }


        tv_resv.setOnClickListener {


            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "ReserveListFragment")
                .navigation()
        }


    }


    fun loadData() {
        ApiServices.getInstance().create(OrderService::class.java).nav().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {


                    rl0.visibility = View.GONE
                    ll0.visibility = View.VISIBLE


                    val listNav = ArrayList<String>()
                    listNav.clear()

                    val orderNavBean = Gson().fromJson(response, Array<OrderNavBean>::class.java)
                    orderNavBean.forEach {
                        listNav.add(it.title)
                    }


                    val constellationView = layoutInflater.inflate(R.layout.custom_layout, null)

                    val constellation = constellationView.findViewById<GridView>(R.id.constellation)

                    val constellationAdapter = ConstellationAdapter(context, listNav)

                    constellation.adapter = constellationAdapter



                    constellation.setOnItemClickListener { parent, view, position, id ->
                        constellationAdapter.setCheckItem(position)
                        dropDownMenu.setTabText(listNav[position])
                        dropDownMenu.closeMenu()

                        m3_1?.loadData(orderNavBean[position].value)

                    }

                    popupViews.add(constellationView)


                    val contentView = View.inflate(context, R.layout.main3fragmentcontent, null)

                    val mTabLayout = contentView.findViewById<SlidingTabLayout>(R.id.mTabLayout)
                    val mViewPager = contentView.findViewById<androidx.viewpager.widget.ViewPager>(R.id.mViewPager)


                    dropDownMenu.setDropDownMenu(Arrays.asList(*headers), popupViews, contentView)

                    mViewPager.offscreenPageLimit = 4
                    mViewPager.adapter = Main2ViewPagerAdapter(childFragmentManager, mFragments, mTitles)
                    mTabLayout.setViewPager(mViewPager)


                }

                override fun onFault(message: String) {
                    rl0.visibility = View.VISIBLE
                    ll0.visibility = View.GONE
                }

            })
    }


    override fun getLayoutRes(): Int = R.layout.main3fragment
}