package com.yjhh.ppwcustomer.ui.fragment

import android.graphics.Rect
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main1FragmentAdapter
import com.yjhh.ppwcustomer.bean.Main1FootBean
import com.yjhh.ppwcustomer.bean.MainFinalDataBean
import com.yjhh.ppwcustomer.common.utils.GlideImageLoader

import com.yjhh.ppwcustomer.present.SectionMain1Present
import com.yjhh.ppwcustomer.view.Main1View
import kotlinx.android.synthetic.main.main1fragment.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.yjhh.ppwcustomer.adapter.RvAdapter
import com.yjhh.ppwcustomer.bean.Main1HeadBean


class Main1Fragment : BaseFragment(), Main1View, OnRefreshListener,
    OnLoadMoreListener {
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        isRefresh = false
        startindex++
        sectionMain1Present.joinMain(startindex, pageSize)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        isRefresh = true
        startindex = 0
        sectionMain1Present.joinMain(startindex, pageSize)
        refreshLayout.finishRefresh()
    }


    var startindex = 0
    val pageSize = 7

    var isRefresh = true

    override fun getLayoutRes(): Int = R.layout.main1fragment


    override fun onSuccess(main1bean: MainFinalDataBean) {

        if (main1bean.main1HeadBean != null) {
            val bannerImage = ArrayList<String>()
            main1bean.main1HeadBean.banners.forEach {
                bannerImage.add(it.imageUrl)
            }
            banner.setImages(bannerImage)
                .setImageLoader(GlideImageLoader())
                .setDelayTime(5000)
                .start()


            val list = ArrayList<Main1HeadBean.TabsBean>()

            if (main1bean.main1HeadBean.tabs != null) {
                main1bean.main1HeadBean.tabs.forEach { mutableList ->
                    mutableList.forEach {
                        list.add(it)
                    }
                }
            }


            rv_Grid.adapter = RvAdapter(context, list)

        } else {

        }


        if (main1bean.main1FootBean != null) {
            setData(isRefresh, main1bean.main1FootBean.items)
        }


    }

    override fun onFault(errorMsg: String?) {

    }


    lateinit var sectionMain1Present: SectionMain1Present
    lateinit var mAdapter: Main1FragmentAdapter
    override fun initView(rootView: View?) {

        sectionMain1Present = SectionMain1Present(context, this)
        sectionMain1Present.joinMain(startindex, pageSize)
        val list = ArrayList<Main1FootBean.ItemsBean>()
        mAdapter = Main1FragmentAdapter(context,list)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = mAdapter

        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener(this)
        swipeLayout.setOnLoadMoreListener(this)
        swipeLayout.setEnableRefresh(true)




        rv_Grid.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        rv_Grid.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

                outRect.left = 10
                outRect.top = 10
                outRect.top = 10
            }
        })

    }


    fun setData(isRefresh: Boolean, data: ArrayList<Main1FootBean.ItemsBean>) {

        if (isRefresh) {
            startindex = 0
            mAdapter.setRefreshData(data)
        } else {
            if (data.isNotEmpty()) {
                if (data.size == pageSize) {
                    mAdapter.setLoadMoreData(data)

                } else {
                    mAdapter.setLoadMoreData(data)

                    Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show()




                }
            } else {

                Toast.makeText(context, "数据加载失败", Toast.LENGTH_LONG).show()
            }
        }


    }


}