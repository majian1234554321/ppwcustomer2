package com.yjhh.ppwcustomer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
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
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.MyAddressAdapter
import com.yjhh.ppwcustomer.adapter.RecentlyBrowseAdapter
import com.yjhh.ppwcustomer.bean.MyAddressBean
import com.yjhh.ppwcustomer.bean.RecentlyBrowseBean
import com.yjhh.ppwcustomer.bean.rxbusbean.RxAddressBean
import com.yjhh.ppwcustomer.present.SectionUselessPresent
import com.yjhh.ppwcustomer.present.SectionUserPresent
import com.yjhh.ppwcustomer.view.MyAddressView
import com.yjhh.ppwcustomer.view.RecentlyBrowseView
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.myaddressfragment.*
import android.support.v7.widget.DividerItemDecoration
import com.yjhh.ppwcustomer.R.id.recyclerView




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
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mRecyclerView.addItemDecoration(DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL))

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
