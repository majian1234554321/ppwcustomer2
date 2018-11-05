package com.yjhh.ppwcustomer.ui.fragment

import android.nfc.tech.MifareUltralight
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.api.SectionOrderService
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2FragmentAdapter
import com.yjhh.ppwcustomer.bean.Main1FootBean
import com.yjhh.ppwcustomer.bean.MainFinalDataBean
import com.yjhh.ppwcustomer.present.SectionOrderPresent
import com.yjhh.ppwcustomer.view.SectionOrderView

import kotlinx.android.synthetic.main.main2_1fragment.*

class Main2_1Fragment : BaseFragment(), SectionOrderView {


    override fun onSuccess(main1bean: MainFinalDataBean, flag: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if (main1bean.main1FootBean != null) {

            if ("refresh" == flag) {
                setData(true, main1bean.main1FootBean.items)
                mAdapter.setEnableLoadMore(true)
            } else {
                val isRefresh = startindex == 1
                setData(isRefresh, main1bean.main1FootBean.items)
            }

        }

    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var startindex = 0
    val pageSize = 10

    override fun getLayoutRes(): Int = R.layout.main2_1fragment


    val mAdapter = Main2FragmentAdapter()
    lateinit var present: SectionOrderPresent

    override fun initView(rootView: View?) {

        present = SectionOrderPresent(context)

        mAdapter.setOnLoadMoreListener { loadMore() }
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initRefreshLayout()

        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = mAdapter
        refresh("refresh")

    }


    override fun initData() {

        swipeLayout.autoRefresh()

    }


    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh("refresh")
            refreshLayout.finishRefresh()
        }
    }

    private fun refresh(flag: String) {
        startindex = 0
        mAdapter.setEnableLoadMore(false)//这里的作用是防止下拉刷新的时候还可以上拉加载
        // sectionOrderPresent.joinMain(startindex, pageSize, flag);
    }

    private fun loadMore() {
        // sectionMain1Present.joinMain(startindex, pageSize, "load")

    }

    fun setData(isRefresh: Boolean, data: List<Main1FootBean.ItemsBean>) {
        startindex++
        val size = data.size
        if (isRefresh) {
            mAdapter.setNewData(data)
        } else {
            if (size > 0) {
                mAdapter.addData(data)
            }
        }
        if (size < MifareUltralight.PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh)
            Toast.makeText(context, "no more data", Toast.LENGTH_SHORT).show()
        } else {
            mAdapter.loadMoreComplete()
        }
    }

}