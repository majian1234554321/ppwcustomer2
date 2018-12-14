package com.paipaiwei.takeout_personal.ui.activity.coupon

import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.CouponAdapter
import com.paipaiwei.takeout_personal.adapter.CouponFragmentAdapter
import com.paipaiwei.takeout_personal.bean.CouponBean
import com.paipaiwei.takeout_personal.present.SectionCouponPresent
import com.paipaiwei.takeout_personal.view.CouponView
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
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)


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