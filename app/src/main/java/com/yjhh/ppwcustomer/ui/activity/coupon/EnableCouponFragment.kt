package com.yjhh.ppwcustomer.ui.activity.coupon

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.CouponAdapter
import com.yjhh.ppwcustomer.adapter.CouponFragmentAdapter
import com.yjhh.ppwcustomer.bean.CouponBean
import com.yjhh.ppwcustomer.present.SectionCouponPresent
import com.yjhh.ppwcustomer.view.CouponView
import kotlinx.android.synthetic.main.enablecouponfragment.*

class EnableCouponFragment : BaseFragment(), CouponView {

    val status = "1"    // 状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    private val startindex = 0
    val pageSize = 100

    override fun onSuccess(main1bean: CouponBean, flag: String) {
        recyclerView.adapter = CouponAdapter(main1bean.items)
    }

    override fun onFault(errorMsg: String?) {

    }

    lateinit var sectionCouponPresent: SectionCouponPresent

    override fun getLayoutRes(): Int = R.layout.enablecouponfragment

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        sectionCouponPresent = SectionCouponPresent(context, this)

        initRefreshLayout()
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")
    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")

    }


}