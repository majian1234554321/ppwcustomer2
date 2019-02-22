package com.paipaiwei.personal.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Collection123Adapter

import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.paipaiwei.personal.present.SectionUselessPresent
import com.paipaiwei.personal.view.RecentlyBrowseView
import com.yjhh.common.view.WrapContentLinearLayoutManager
import kotlinx.android.synthetic.main.collectionmain123.*

class Collection1Fragment : BaseFragment(), RecentlyBrowseView {
    var startindex = 0
    val pageSize = 10
    var type = "0"    //默认null（null/0收藏 1足迹）
    val itemType = "1"//默认null( null/0 店铺 1商品 2 文章)
    override fun getLayoutRes(): Int = R.layout.collectionmain123


    private  var mAdapter: Collection123Adapter? = null
    lateinit var sectionUselessPresent: SectionUselessPresent

    val lists = ArrayList<RecentlyBrowseBean.ItemsBean>()
    override fun initView() {

        val typeValue = arguments?.getString("type")

        type = if ("收藏" == typeValue) {
            "0"
        } else {
            "1"
        }



        sectionUselessPresent = SectionUselessPresent(context, this)

        mAdapter = Collection123Adapter(lists)

        swipeLayout.setRefreshHeader(ClassicsHeader(context))

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mRecyclerView.layoutManager = WrapContentLinearLayoutManager(context)


        mRecyclerView.adapter = mAdapter


        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


        mAdapter?.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantHomeFragment")
                .withString("id", mAdapter?.data?.get(position)?.itemId)
                .navigation()
        }


        refresh()

    }


    private fun refresh() {
        startindex = 0
        sectionUselessPresent.usercollect(type, itemType, startindex, pageSize, "refresh")

    }


    private fun loadMore() {

        startindex++
        sectionUselessPresent.usercollect(type, itemType, startindex, pageSize, "load")

    }


    override fun onSuccess(main1bean: RecentlyBrowseBean, flag: String) {
        if ("refresh" == flag) {



            if (startindex==0&&main1bean.items.isEmpty()) {
                mAdapter?.data?.clear()
                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view
            }else{
                mAdapter?.setNewData(main1bean.items)
                mAdapter?.disableLoadMoreIfNotFullPage()
            }


        } else {

            if (main1bean.items.size < pageSize) {
                mAdapter?.loadMoreEnd()
            } else {
                mAdapter?.loadMoreEnd()
            }

        }
    }

    override fun onFault(errorMsg: String?) {


    }


    companion object {
        fun newInstance(type: String?): Collection1Fragment {
            val fragment = Collection1Fragment()
            val bundle = Bundle()
            bundle.putString("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

}