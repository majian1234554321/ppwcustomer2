package com.paipaiwei.personal.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Collection123Adapter
import com.paipaiwei.personal.adapter.RecentlyBrowseAdapter
import com.paipaiwei.personal.bean.MyMessageBean
import com.paipaiwei.personal.bean.RecentlyBrowseBean
import com.paipaiwei.personal.present.SectionUselessPresent
import com.paipaiwei.personal.view.RecentlyBrowseView
import kotlinx.android.synthetic.main.collectionmain123.*

class Collection1Fragment : BaseFragment(), RecentlyBrowseView {
    var startindex = 0
    val pageSize = 10
    var type = "0"    //默认null（null/0收藏 1足迹）
    val itemType = "0"//默认null( null/0 店铺 1商品 2 文章)
    override fun getLayoutRes(): Int = R.layout.collectionmain123


    private lateinit var mAdapter: Collection123Adapter
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



            if (startindex==0&&main1bean.items.isEmpty()) {
                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter.emptyView = view
            }else{
                mAdapter.setNewData(main1bean.items)
                mAdapter.disableLoadMoreIfNotFullPage()
            }


        } else {

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