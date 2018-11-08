package com.yjhh.ppwcustomer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.integralfragment.*

class IntegralFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.integralfragment

    override fun initView() {

        /* swipeLayout.setRefreshHeader(ClassicsHeader(context))
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mAdapter
        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()
    }


    private fun refresh() {

        startindex = 0
        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()

        startindex++
        sectionCouponPresent.coupon(status, startindex, pageSize, "load")

    }


    override fun initData() {
        Log.i("TAG", "Main2_1Fragment")

    }*/


    }
}