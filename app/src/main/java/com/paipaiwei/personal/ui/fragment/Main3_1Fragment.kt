package com.paipaiwei.personal.ui.fragment


import android.util.Log

import android.widget.Toast

import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R

import com.paipaiwei.personal.adapter.PullToRefreshAdapter

import com.paipaiwei.personal.present.SectionOrderPresent


import kotlinx.android.synthetic.main.main2_1fragment.*

class Main3_1Fragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    var pageIndex = 0
    val pageSize = 15

    var present: SectionOrderPresent? = null
    var mAdapter: PullToRefreshAdapter? = null

    override fun initView() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()
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
        mAdapter?.setNewData(getData())
    }

    private fun loadMore() {
        mAdapter?.addData(getData())
        mAdapter?.loadMoreEnd()

    }

    fun getData(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("A")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        return list
    }

    override fun initData() {
        Log.i("TAG", "Main2_1Fragment")

    }


}