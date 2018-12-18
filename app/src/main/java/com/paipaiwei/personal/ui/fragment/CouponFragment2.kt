/*
package com.paipaiwei.personal.ui.fragment

import android.support.v7.widget.LinearLayoutManager

import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.CouponFragmentAdapter
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.present.SectionCouponPresent
import com.paipaiwei.personal.view.CouponView
import kotlinx.android.synthetic.main.couponfragment.*


class CouponFragment2 : BaseFragment(), CouponView {

    var startindex = 0
    val pageSize = 10

    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)

    override fun onSuccess(main1bean: CouponBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.setNewData(main1bean.items)
        } else {
            mAdapter.addData(main1bean.items)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
    }

    override fun initData() {

    }


    override fun getLayoutRes(): Int = R.layout.couponfragment


     var mAdapter: CouponFragmentAdapter = CouponFragmentAdapter()

    lateinit var sectionCouponPresent: SectionCouponPresent
    override fun initView() {
        sectionCouponPresent = SectionCouponPresent(context, this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()
    }

    private fun initAdapter() {
        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)


        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        startindex = 0
        sectionCouponPresent.coupon(status, startindex, pageSize, "refresh")


    }


    private fun loadMore() {
        startindex++
        sectionCouponPresent.coupon(status, startindex, pageSize, "load")


    }








}*/
