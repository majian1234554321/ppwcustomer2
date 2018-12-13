package com.yjhh.ppwcustomer.ui.fragment

import android.nfc.tech.MifareUltralight
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yjhh.common.api.SectionOrderService
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main2FragmentAdapter
import com.yjhh.ppwcustomer.adapter.PullToRefreshAdapter
import com.yjhh.ppwcustomer.bean.Main1FootBean
import com.yjhh.ppwcustomer.bean.MainFinalDataBean
import com.yjhh.ppwcustomer.present.SectionOrderPresent
import com.yjhh.ppwcustomer.view.SectionOrderView
import kotlinx.android.synthetic.main.main1fragment.view.*

import kotlinx.android.synthetic.main.main2_1fragment.*

class Main3_1Fragment : BaseFragment() {



    var pageIndex = 0
    val pageSize = 15
    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    lateinit var present: SectionOrderPresent
    override fun initView() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()



    }
     var  mAdapter : PullToRefreshAdapter = PullToRefreshAdapter()

    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener ({
            loadMore()
        },mRecyclerView)



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

        mAdapter.setNewData(getData())
        mAdapter.setEnableLoadMore(false)

        if (true){
            mAdapter.setEnableLoadMore(true)
        }else{
            mAdapter.setEnableLoadMore(true)
        }

    }


    private fun loadMore() {

        mAdapter.addData(getData())


        mAdapter.loadMoreEnd()


    }





    fun getData():ArrayList<String>{
        val list= ArrayList<String>()
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
        Log.i("TAG","Main2_1Fragment")

    }









}