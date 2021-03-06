package com.paipaiwei.personal.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.CouponFragmentAdapter
import com.paipaiwei.personal.adapter.RecentlyBrowseAdapter
import com.paipaiwei.personal.bean.CouponBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.paipaiwei.personal.present.SectionUselessPresent
import com.paipaiwei.personal.view.RecentlyBrowseView
import kotlinx.android.synthetic.main.couponfragment.*


class RecentlyBrowseFragment : BaseFragment(), RecentlyBrowseView {

    var startindex = 0
    val pageSize = 10
    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    override fun getLayoutRes(): Int = R.layout.recentlybrowsefragment

    val list = ArrayList<RecentlyBrowseBean.ItemsBean>()
    private lateinit var mAdapter: RecentlyBrowseAdapter
    lateinit var sectionUselessPresent: SectionUselessPresent


    override fun initView() {
        sectionUselessPresent =  SectionUselessPresent(context,this)
        mAdapter = RecentlyBrowseAdapter(list, context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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
        sectionUselessPresent.userhistory(status, startindex, pageSize, "refresh")

    }


    private fun loadMore() {

        startindex++
        sectionUselessPresent.userhistory(status, startindex, pageSize, "load")

    }


    override fun onSuccess(main1bean: RecentlyBrowseBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.onRefresh(main1bean.items as ArrayList<RecentlyBrowseBean.ItemsBean>)

        } else {
            mAdapter.onLoad(main1bean.items as ArrayList<RecentlyBrowseBean.ItemsBean>)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
        //
    }



}