package com.paipaiwei.takeout_personal.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.SectionUserService
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.listener.LeftOnClickListener

import com.yjhh.common.utils.RxBus
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.MyAddressAdapter
import com.paipaiwei.takeout_personal.adapter.RecentlyBrowseAdapter
import com.paipaiwei.takeout_personal.bean.MyAddressBean
import com.paipaiwei.takeout_personal.bean.RecentlyBrowseBean
import com.paipaiwei.takeout_personal.bean.rxbusbean.RxAddressBean
import com.paipaiwei.takeout_personal.present.SectionUselessPresent
import com.paipaiwei.takeout_personal.present.SectionUserPresent
import com.paipaiwei.takeout_personal.view.MyAddressView
import com.paipaiwei.takeout_personal.view.RecentlyBrowseView
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.myaddressfragment.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.paipaiwei.takeout_personal.R.id.recyclerView




class MyAddressFragment : BaseFragment(), View.OnClickListener, MyAddressView {


    var startindex = 0
    val pageSize = 10


    val list = ArrayList<MyAddressBean.ItemsBean>()
    private lateinit var mAdapter: MyAddressAdapter
    lateinit var sectionUserPresent: SectionUserPresent


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_addAddress -> {
                val bean = RxAddressBean()
                bean.flag = "ADD"
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "AddressADUFragment")
                    .withSerializable("bean", bean)
                    .navigation()
            }
            else -> {
            }
        }
    }


    override fun getLayoutRes(): Int = R.layout.myaddressfragment

    override fun initView() {



        val dis = RxBus.default.toFlowable(RxAddressBean::class.java).subscribe {
            if (it != null) {
                when (it.flag) {
                    "ADD" -> {
                        Toast.makeText(context, "ADD", Toast.LENGTH_SHORT).show()
                    }

                    "UPDATE" -> {

                    }

                    "DELETE" -> {

                    }

                    else -> {
                        swipeLayout.autoRefresh()
                    }
                }
            }
        }


        compositeDisposable.add(dis)


        tv_addAddress.setOnClickListener(this)


        sectionUserPresent = SectionUserPresent(context, this)


        mAdapter = MyAddressAdapter(list, context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)

        mRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                mActivity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        mRecyclerView.adapter = mAdapter
        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()



        mAdapter.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(context, "po", Toast.LENGTH_SHORT).show()
        }


    }


    private fun refresh() {
        startindex = 0
        sectionUserPresent.getAllUserAddress("", startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        sectionUserPresent.getAllUserAddress("", startindex, pageSize, "load")

    }


    override fun onSuccess(main1bean: MyAddressBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.onRefresh(main1bean.items as ArrayList<MyAddressBean.ItemsBean>)

        } else {
            mAdapter.onLoad(main1bean.items as ArrayList<MyAddressBean.ItemsBean>)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
