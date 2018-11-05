package com.yjhh.ppwcustomer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.CouponFragmentAdapter
import kotlinx.android.synthetic.main.couponfragment.*

class CouponFragment : BaseFragment(), OnRefreshListener, OnLoadMoreListener {
    override fun onLoadMore(refreshLayout: RefreshLayout) {

        myAdapter.addData(getData())


        mSmartRefreshLayout.finishLoadMore()
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        mSmartRefreshLayout.finishRefresh()
    }


    override fun getLayoutRes(): Int = R.layout.couponfragment


    lateinit var myAdapter:CouponFragmentAdapter

    override fun initView() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mSmartRefreshLayout.setRefreshHeader(ClassicsHeader(context))


        mSmartRefreshLayout.setRefreshFooter(ClassicsFooter(context))


        mSmartRefreshLayout.setOnRefreshListener(this)

        mSmartRefreshLayout.setEnableLoadMore(true)
        mSmartRefreshLayout.setOnLoadMoreListener(this)

          myAdapter = CouponFragmentAdapter(context,getData())


        myAdapter.addData(getData())

        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = myAdapter




    }


    fun getData(): ArrayList<String> {
        var list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")

        return list
    }


}