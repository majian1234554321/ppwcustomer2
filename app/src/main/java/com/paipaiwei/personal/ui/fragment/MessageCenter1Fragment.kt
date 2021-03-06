package com.paipaiwei.personal.ui.fragment

import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.SpaceItemDecoration

import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.MyMessageFragmentAdapter
import com.paipaiwei.personal.bean.MyMessageBean
import com.paipaiwei.personal.present.SectionUselessPresent
import com.paipaiwei.personal.view.MyMessageView

import kotlinx.android.synthetic.main.messagecenter1fragment.*


class MessageCenter1Fragment : BaseFragment(), MyMessageView {

    val array = arrayOf("我的消息", "系统消息")
    override fun getLayoutRes(): Int = R.layout.messagecenter1fragment


    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    override fun onSuccess(main1bean: MyMessageBean, flag: String) {
        if ("refresh" == flag) {
            if (swipeLayout != null) {
                swipeLayout.finishRefresh()
            }

            if (startindex==0&&main1bean.items.size==0){
                val view = View.inflate(mActivity, com.ppwc.restaurant.R.layout.emptyview, null)
                view.findViewById<TextView>(com.ppwc.restaurant.R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view
            }else{
                mAdapter.setNewData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
            }


        } else {
            mAdapter.addData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
        //
    }

    var startindex = 0
    val pageSize = 10

    var share = "1"


    lateinit var sectionCouponPresent: SectionUselessPresent


    var list = ArrayList<MyMessageBean.ItemsBean>()

    lateinit var mAdapter: MyMessageFragmentAdapter

    override fun initView() {


        mAdapter = MyMessageFragmentAdapter(list)
        sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()


        mAdapter.setOnItemClickListener { adapter, view, position ->


            (parentFragment as BaseFragment).start(
                MessageDetailFragment.newInstance(adapter.data[position] as MyMessageBean.ItemsBean)
            )

        }


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

        }


    }


    private fun refresh() {
        startindex = 0
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "refresh")

    }


    private fun loadMore() {

        startindex++
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }


}
