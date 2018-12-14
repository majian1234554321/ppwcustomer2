package com.paipaiwei.takeout_personal.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.tencent.mm.opensdk.utils.Log
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.SpaceItemDecoration

import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.MyMessageFragmentAdapter
import com.paipaiwei.takeout_personal.bean.MyMessageBean
import com.paipaiwei.takeout_personal.present.SectionUselessPresent
import com.paipaiwei.takeout_personal.view.MyMessageView

import kotlinx.android.synthetic.main.messagecenter1fragment.*


class MessageCenter2Fragment : BaseFragment(), MyMessageView {

    val array = arrayOf("我的消息", "系统消息")
    override fun getLayoutRes(): Int = R.layout.messagecenter1fragment


    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    override fun onSuccess(main1bean: MyMessageBean, flag: String) {
        if ("refresh" == flag) {
            if (swipeLayout!=null) {
                swipeLayout.finishRefresh()
            }

            mAdapter.setNewData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)

        } else {
            mAdapter.addData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var startindex = 0
    val pageSize = 10

    var share = "0"


    lateinit var sectionCouponPresent: SectionUselessPresent


    var list = ArrayList<MyMessageBean.ItemsBean>()

    lateinit var mAdapter: MyMessageFragmentAdapter

    override fun initView() {



        mRecyclerView.addItemDecoration(SpaceItemDecoration(30))
        mAdapter = MyMessageFragmentAdapter(list)
        sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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


    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        startindex = 0
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()

        startindex++
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }



}
