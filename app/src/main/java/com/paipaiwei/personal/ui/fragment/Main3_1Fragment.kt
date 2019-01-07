package com.paipaiwei.personal.ui.fragment


import android.util.Log

import android.widget.Toast

import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R

import com.paipaiwei.personal.adapter.PullToRefreshAdapter
import com.paipaiwei.personal.present.OrderPresent

import com.paipaiwei.personal.present.SectionOrderPresent
import com.paipaiwei.personal.view.OrderView


import kotlinx.android.synthetic.main.main2_1fragment.*

class Main3_1Fragment : BaseFragment(), OrderView {
    override fun onSuccessOrder(response: String?, flag: String?) {

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    var pageIndex = 0
    val pageSize = 15
    var type = ""
    var status = ""

    var present: OrderPresent? = null
    var mAdapter: PullToRefreshAdapter? = null

    override fun initView() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()

        present = OrderPresent(mActivity, this)

    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mAdapter = PullToRefreshAdapter()
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
        }


    }

    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
    }

    private fun refresh() {
        pageIndex = 0
        present?.myOrders(type, status, pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        present?.myOrders(type, status, pageIndex, pageSize, "loadMore")

    }


    override fun initData() {
        Log.i("TAG", "Main2_1Fragment")

    }


}