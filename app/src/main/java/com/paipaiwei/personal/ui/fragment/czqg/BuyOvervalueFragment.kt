package com.paipaiwei.personal.ui.fragment.czqg

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.QiangPaiListBean
import com.paipaiwei.personal.ui.fragment.QiangPaiFragment
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.common.BaseApplication
import com.yjhh.common.api.ApiServices
import com.yjhh.common.api.ProcessObserver2
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.base.BaseView
import com.yjhh.common.present.BasePresent
import com.yjhh.common.utils.ImageLoaderUtils
import io.reactivex.Observable
import kotlinx.android.synthetic.main.buyovervaluefragment.*


class BuyOvervalueFragment : BaseFragment(), BuyOvervalueService.BuyOvervalueView {
    override fun onBuyOvervalue(response: String?, flag: String?) {

        Log.i("BuyOvervalueFragment", response)

        when (flag) {
            "overBynav" -> {
                val model = Gson().fromJson<OverByNavBean>(response, OverByNavBean::class.java)

                model.navs.forEach {
                    mTabLayout.addTab(it.title)
                }



                mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabReselected(p0: TabLayout.Tab?) {

                    }

                    override fun onTabUnselected(p0: TabLayout.Tab?) {
                        // TODO("not implemented") //To change body of created functions use File ||
                    }

                    override fun onTabSelected(p0: TabLayout.Tab?) {
                        if (p0 != null) {
                            tradeCode = model.navs[p0.position].title
                            initRefreshLayout()
                        }
                    }

                })


            }
            "refresh" -> {

                val listBean = ArrayList<MultiItemEntity>()
                listBean.clear()
                val listModel = Gson().fromJson<BuyOverListBean>(response, BuyOverListBean::class.java)

                if (listModel?.items != null && listModel.items.isNotEmpty()) {
                    listModel.items.forEach { item ->
                        listBean.add(item)
                        item.qiangPai.forEach {
                            listBean.add(it)
                        }
                    }
                    mAdapter?.setNewData(listBean)
                } else {

                    val view = View.inflate(mActivity, R.layout.emptyview, null)
                    view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                    mAdapter?.data?.clear()
                    mAdapter?.notifyDataSetChanged()
                    mAdapter?.emptyView = view
                }


            }

            "loadMore" -> {

                val listBean = ArrayList<MultiItemEntity>()
                listBean.clear()
                val listModel = Gson().fromJson<BuyOverListBean>(response, BuyOverListBean::class.java)

                if (listModel?.items != null && listModel.items.isNotEmpty()) {

                    listModel.items.forEach { item ->
                        listBean.add(item)
                        item.qiangPai.forEach {
                            listBean.add(it)
                        }
                    }
                    mAdapter?.addData(listBean)

                    if (listModel.items.size == pageSize) {
                        mAdapter?.loadMoreComplete()
                    } else {
                        mAdapter?.loadMoreEnd()
                    }
                } else {
                    mAdapter?.loadMoreEnd()
                }


            }
            else -> {
            }
        }

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.buyovervaluefragment


    override fun initView() {
        ImmersionBar.setTitleBar(activity, tbv_title)

        present = BuyOvervaluePresent(mActivity, this)
        present?.overBynav("overBynav")

        initAdapter()
        initRefreshLayout()


    }

    var present: BuyOvervaluePresent? = null
    var pageIndex = 0
    val pageSize = 15
    var tradeCode: String? = "" //类型 0今日抢拍 1 明日抢拍
    val listValue = ArrayList<MultiItemEntity>()
    var mAdapter: BuyOvervalueAdapter? = null


    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()
    }

    private fun initAdapter() {

        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = BuyOvervalueAdapter(listValue)
        mRecyclerView.adapter = mAdapter


        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter?.setOnItemClickListener { adapter, view, position ->


            if (adapter.data[position] is Item) {
                ARouter.getInstance()
                    .build("/RestaurantActivity/Restaurant")
                    .withString("displayTab", "RestaurantHomeFragment")
                    .withString("id", (adapter.data[position] as Item).shopId)
                    .navigation()

            } else if (adapter.data[position] is QiangPai) {
                start(
                    BuyOvervalueDetailsFragment.newInstance(
                        (adapter.data[position] as QiangPai).id,
                        (adapter.data[position] as QiangPai).type, "STATUS"
                    )
                )
            }

        }

        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            start(
                BuyOvervalueDetailsFragment.newInstance(
                    (adapter.data[position] as QiangPai).id,
                    (adapter.data[position] as QiangPai).type,"STATUS"
                )
            )
        }


    }

    private fun refresh() {
        pageIndex = 0
        present?.overByList(tradeCode, pageIndex, pageSize, "refresh")

    }


    private fun loadMore() {
        pageIndex++
        present?.overByList(tradeCode, pageIndex, pageSize, "loadMore")

    }


    class BuyOvervaluePresent(var context: Context, var view: BuyOvervalueService.BuyOvervalueView) : BasePresent() {
        fun overByList(tradeCode: String?, pageIndex: Int, pageSize: Int, flag: String?) {
            map.clear()
            map["tradeCode"] = tradeCode
            map["pageIndex"] = pageIndex.toString()
            map["pageSize"] = pageSize.toString()
            toSubscribe2(ApiServices.getInstance().create(BuyOvervalueService::class.java).overByList(map),
                object : ProcessObserver2(context) {
                    override fun processValue(response: String?) {
                        view.onBuyOvervalue(response, flag)
                    }

                    override fun onFault(message: String) {
                        view.onFault(message)
                    }

                })
        }

        fun overBynav(flag: String?) {
            toSubscribe2(ApiServices.getInstance().create(BuyOvervalueService::class.java).overBynav(),
                object : ProcessObserver2(context) {
                    override fun processValue(response: String?) {
                        view.onBuyOvervalue(response, flag)
                    }

                    override fun onFault(message: String) {
                        view.onFault(message)
                    }

                })
        }

    }


    data class OverByNavBean(
        val navs: List<Nav>,
        val title: String
    )

    data class Nav(
        val code: String,
        val ifCurr: Boolean,
        val title: String,
        val value: String
    )


    data class BuyOverListBean(
        val items: List<Item>,
        val recordCount: Int
    )

    data class Item(
        val address: String,
        val grade: Int,
        val qiangPai: List<QiangPai>,
        val renj: String,
        val shopId: String,
        val shopLogoUrl: String,
        val shopName: String,
        val tradeName: String
    ) : MultiItemEntity {
        override fun getItemType(): Int = BuyOvervalueAdapter.TYPE_LEVEL_0
    }

    data class QiangPai(
        val costPrice: Double,
        val count: Int,
        val countText: String,
        val describe: String,
        val id: String,
        val imageUrl: String,
        val markPrice: Double,
        val memo: String,
        val price: Double,
        val rec: Boolean,
        val shopName: String,
        val status: Int,
        val statusText: String,
        val time: Int,
        val title: String,
        val total: Int,
        val type: String
    ) : MultiItemEntity {
        override fun getItemType(): Int = BuyOvervalueAdapter.TYPE_LEVEL_1
    }


    class BuyOvervalueAdapter(data: List<MultiItemEntity>) :
        BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {
        override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {

            when (helper?.itemViewType) {
                TYPE_LEVEL_0 -> {
                    val headValue = item as Item
                    ImageLoaderUtils.load(
                        BaseApplication.getIns(),
                        helper.getView<ImageView>(R.id.iv_headImage),
                        headValue?.shopLogoUrl,
                        R.drawable.icon_place_square,
                        R.drawable.icon_place_square,
                        0
                    )

                    helper?.setText(R.id.tv_shopName, headValue.shopName)
                        .setText(R.id.tv_renjun, headValue.renj)
                        .setText(R.id.tv_address, headValue.address)
                        .setText(R.id.tv_type, headValue.tradeName)

                }

                TYPE_LEVEL_1 -> {
                    val footValue = item as QiangPai

                    ImageLoaderUtils.load(
                        BaseApplication.getIns(),
                        helper.getView<ImageView>(R.id.iv_image),
                        footValue?.imageUrl,
                        R.drawable.icon_place_square,
                        R.drawable.icon_place_square,
                        0
                    )

                    when (footValue.status) {//状态 0即将开始 1进行中 2已结束/已拍完/已过期
                        0 -> {
                            helper?.setVisible(R.id.mb_pay, false)


                        }
                        1 -> {
                            helper?.setVisible(R.id.mb_pay, true)
                                .addOnClickListener(R.id.mb_pay)
                        }
                        2 -> {
                            helper?.setVisible(R.id.mb_pay, false)


                        }

                        else -> {

                        }
                    }

                    helper?.setText(R.id.tv_couName, footValue.title)
                        .setText(R.id.tv_countText, footValue.countText)
                }
            }
        }


        init {
            addItemType(BuyOvervalueAdapter.TYPE_LEVEL_0, R.layout.buyovervalueadapter_head)
            addItemType(BuyOvervalueAdapter.TYPE_LEVEL_1, R.layout.buyovervalueadapter)
        }

        companion object {
            const val TYPE_LEVEL_0 = 0
            const val TYPE_LEVEL_1 = 1

        }


    }

}