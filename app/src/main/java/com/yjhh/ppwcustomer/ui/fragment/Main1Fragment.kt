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

import android.util.Log
import com.scwang.smartrefresh.layout.footer.ClassicsFooter


import com.yjhh.ppwcustomer.bean.Main1HeadBean


class Main1Fragment : BaseFragment(), Main1View {
    override fun getLayoutRes(): Int = R.layout.main1fragment

    override fun initView(v: View) {
        mAdapter = Main1FragmentAdapter()
        sectionMain1Present = SectionMain1Present(context, this)

        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setRefreshFooter(ClassicsFooter(context))

        initRefreshLayout()
        // mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = mAdapter

        swipeLayout.setOnLoadMoreListener {
            loadMore()
        }
        swipeLayout.autoRefresh()

    }




    var startindex = 0
    val pageSize = 10


    override fun onSuccess(main1bean: MainFinalDataBean, flag: String) {

        swipeLayout.finishLoadMore()
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
            list.clear()
            if (main1bean.main1HeadBean.tabs != null) {
                main1bean.main1HeadBean.tabs.forEach { mutableList ->
                    mutableList.forEach {
                        list.add(it)
                    }
                }
            }


            mGridViewPager
                //设置每一页的容量
                .setPageSize(10)
                .setGridItemClickListener { pos, position, str ->
                    Log.d(
                        "123",
                        pos.toString() + "/" + str + position
                    )


                }
                .setGridItemLongClickListener { pos, position, str ->
                    Log.d(
                        "456",
                        pos.toString() + "/" + str
                    )
                }
                .init(list)


        } else {

        }



        if (main1bean.main1FootBean != null) {

            if ("refresh" == flag) {
                setData(true, main1bean.main1FootBean.items)
                //  mAdapter.setEnableLoadMore(true)
            } else {
                val isRefresh = startindex == 1
                setData(isRefresh, main1bean.main1FootBean.items)
            }

        }


    }

    override fun onFault(errorMsg: String?) {
        swipeLayout.finishLoadMore()
    }

    lateinit var mAdapter: Main1FragmentAdapter
    lateinit var sectionMain1Present: SectionMain1Present


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh("refresh")
            refreshLayout.finishRefresh()
        }
    }

    private fun refresh(flag: String) {
        startindex = 0
        mAdapter.setEnableLoadMore(false)//这里的作用是防止下拉刷新的时候还可以上拉加载
        sectionMain1Present.joinMain(startindex, pageSize, flag)
    }

    private fun loadMore() {
        sectionMain1Present.joinMain(startindex, pageSize, "load")

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
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh)
            Toast.makeText(context, "no more data", Toast.LENGTH_SHORT).show()
        } else {
            mAdapter.loadMoreComplete()
        }
    }


}