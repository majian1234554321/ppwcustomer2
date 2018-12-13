package com.yjhh.ppwcustomer.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Collection123Adapter
import com.yjhh.ppwcustomer.adapter.RecentlyBrowseAdapter
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean
import com.yjhh.ppwcustomer.present.SectionUselessPresent
import com.yjhh.ppwcustomer.view.RecentlyBrowseView
import kotlinx.android.synthetic.main.collectionmain123.*

class FootPrint3Fragment : BaseFragment(), RecentlyBrowseView {
    var startindex = 0
    val pageSize = 10
    val type = "1"    //默认null（null/0收藏 1足迹）
    val itemType = "2"//默认null( null/0 店铺 1商品 2 文章)
    override fun getLayoutRes(): Int = R.layout.collectionmain123


    private lateinit var mAdapter: Collection123Adapter
    lateinit var sectionUselessPresent: SectionUselessPresent

    val lists = ArrayList<RecentlyBrowseBean.ItemsBean>()
    override fun initView() {

        sectionUselessPresent = SectionUselessPresent(context, this)

        mAdapter = Collection123Adapter(lists)

        swipeLayout.setRefreshHeader(ClassicsHeader(context))

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)


        mRecyclerView.adapter = mAdapter


        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


        refresh()

    }


    private fun refresh() {
        startindex = 0
        sectionUselessPresent.usercollect(type, itemType, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        sectionUselessPresent.usercollect(type, itemType, startindex, pageSize, "load")

    }


    override fun onSuccess(main1bean: RecentlyBrowseBean, flag: String) {
        if ("refresh" == flag) {
            // mAdapter.onRefresh(main1bean.items as ArrayList<RecentlyBrowseBean.ItemsBean>)
//            lists.clear()
//            lists.addAll(main1bean.items)
            mAdapter.setNewData(main1bean.items)

            mAdapter.disableLoadMoreIfNotFullPage()
            //mAdapter.notifyDataSetChanged()

        } else {
            //  mAdapter.onLoad(main1bean.items as ArrayList<RecentlyBrowseBean.ItemsBean>)
            if (main1bean.items.size < pageSize) {
                mAdapter.loadMoreEnd()
            } else {
                mAdapter.loadMoreEnd()
            }

        }
    }

    override fun onFault(errorMsg: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun updataContent() {


    }


}