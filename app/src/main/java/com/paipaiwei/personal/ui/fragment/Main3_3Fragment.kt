package com.paipaiwei.personal.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main3_1Adapter
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.bean.Main3_1Bean
import com.paipaiwei.personal.present.OrderPresent
import com.paipaiwei.personal.view.OrderView
import com.yjhh.common.bean.RxOrderBean
import com.yjhh.common.utils.LogUtils
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SpaceItemDecoration2
import kotlinx.android.synthetic.main.main2_1fragment.*



class Main3_3Fragment : BaseFragment(), OrderView {
    override fun onSuccessOrder(response: String?, flag: String?) {
        val model = Gson().fromJson<Main3_1Bean>(response, Main3_1Bean::class.java)
        if ("refresh" == flag) {
            if(model.items!=null&&model.items.isNotEmpty()){
                mAdapter?.setNewData(model.items)
            }else{
                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"

                mAdapter?.data?.clear()
                mAdapter?.notifyDataSetChanged()
                mAdapter?.setEmptyView(R.layout.emptyview,mRecyclerView)
            }

        } else {
            mAdapter?.addData(model.items)

            if (model != null && model.items.size == 15) {
                mAdapter?.loadMoreComplete()
            } else {
                mAdapter?.loadMoreEnd()
            }
        }

    }

    override fun onFault(errorMsg: String?) {

        val view = View.inflate(mActivity, R.layout.emptyview, null)

        view.setOnClickListener {
            ARouter.getInstance()
                .build("/LoginActivity/Login")
                .navigation(mActivity)
        }

        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
        mAdapter?.setNewData(listValue)
        mAdapter?.emptyView = view

    }

    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    var pageIndex = 0
    val pageSize = 15
    var type: String? = ""
    var status = "1"//状态：默认全部null（0全部 1待付款，2已完成，3待评价）

    var present: OrderPresent? = null
    var mAdapter: Main3_1Adapter? = null
    var listValue = ArrayList<Main3_1Bean.ItemsBean>()

    override fun initView() {


        present = OrderPresent(mActivity, this)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()


        val dis2 = RxBus.default.toFlowable(RxOrderBean::class.java).subscribe {
            if (it != null) {
                swipeLayout.autoRefresh()
            }
        }
        compositeDisposable.add(dis2)

        val dis = RxBus.default.toFlowable(LoginBean::class.java).subscribe {
            LogUtils.i("Main4Fragment", it.mobile)
            swipeLayout.autoRefresh()
        }
        compositeDisposable.add(dis)


    }

    override fun onSupportVisible() {
        swipeLayout.autoRefresh()
    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mRecyclerView.addItemDecoration(SpaceItemDecoration2(4, "top"))

        mAdapter = Main3_1Adapter(listValue)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantOrderDetailsFragment")
                .withString("id", (adapter.data[position] as Main3_1Bean.ItemsBean).id)
                .navigation()
        }

        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantOrderDetailsFragment")
                .withString("id", (adapter.data[position] as Main3_1Bean.ItemsBean).id)
                .navigation()
        }


    }

    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
    }

    private fun refresh() {
        pageIndex = 0
        present?.myOrders(type, status, pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        present?.myOrders(type, status, pageIndex, pageSize, "loadMore")

    }


    fun loadData(type: String?) {
        this.type = type
        swipeLayout.autoRefresh()
    }


}