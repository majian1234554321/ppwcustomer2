package com.ppwc.restaurant.views

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.launcher.ARouter
import com.d.lib.xrv.listener.AppBarStateChangeListener
import com.google.android.material.appbar.AppBarLayout
import com.gyf.barlibrary.ImmersionBar
import com.ppwc.restaurant.R

import com.ppwc.restaurant.adapter.Query1Adapter
import com.ppwc.restaurant.adapter.Query4Adapter
import com.ppwc.restaurant.adapter.RestaurantInAdapter
import com.ppwc.restaurant.adapter.RestaurantInTabAdapter
import com.ppwc.restaurant.bean.MeiShiFootBean
import com.ppwc.restaurant.bean.MeiShiHeadBean
import com.ppwc.restaurant.bean.Query1Bean
import com.ppwc.restaurant.ipresent.MeiShiPresent
import com.ppwc.restaurant.ipresent.QueryModelDataPresent
import com.ppwc.restaurant.iview.MeiShiHeadView
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.GlideLoader
import kotlinx.android.synthetic.main.restaurantinfragment.*

class RestaurantInFragment : BaseFragment(), View.OnClickListener, MeiShiHeadView {
    override fun MeiShiFootValue(model: MeiShiFootBean, flag: String?) {


        if ("refresh" == flag) {
            mAdapter?.setNewData(model.items)
        } else {

            mAdapter?.addData(model.items)

            if (pageSize == model.items.size) {
                mAdapter?.loadMoreComplete()
            } else {
                mAdapter?.loadMoreEnd()
            }
        }


    }


    val list4Select = ArrayList<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX.NodesBeanXX>()


    override fun MeiShiHeadValue(model: MeiShiHeadBean) {


        if (model.bannerModels != null && model.bannerModels.size > 0) {

            val imageBanner = ArrayList<String>()

            model.bannerModels.forEach {
                imageBanner.add(it.imageUrl)
            }

            banner.setImages(imageBanner)
                .setImageLoader(GlideLoader())
                .setDelayTime(10000)
                .start()
        }



        if (model.tabsModuleModels != null && model.tabsModuleModels.size > 0) {
            val layoutManager = LinearLayoutManager(mActivity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rv_h.layoutManager = layoutManager
            val mAdapter = RestaurantInTabAdapter(model.tabsModuleModels)
            rv_h.adapter = mAdapter

            mAdapter.setOnItemChildClickListener { adapter, view, position -> }


        }







        model.queryAll.nodes.forEach {
            listAll.add(Query1Bean(it.title, it.value))
        }
        query1Adapter?.notifyDataSetChanged()


        model.queryAuto.nodes.forEach {
            list3.add(Query1Bean(it.title, it.value))
        }
        query3Adapter?.notifyDataSetChanged()



        list4.addAll(model.querySearch.nodes)

        query4Adapter?.notifyDataSetChanged()

    }


    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rb1 -> {

                appBarLayout.setExpanded(false)

                fl4.visibility = View.GONE
                fl3.visibility = View.GONE

                if (fl1.visibility == View.GONE) {
                    fl1.visibility = View.VISIBLE
                } else {
                    fl1.visibility = View.GONE
                }
            }

            R.id.rb2 -> {
                appBarLayout.setExpanded(false)

                fl3.visibility = View.GONE
                fl1.visibility = View.GONE
                fl4.visibility = View.GONE
            }

            R.id.rb3 -> {
                appBarLayout.setExpanded(false)

                fl1.visibility = View.GONE

                fl4.visibility = View.GONE

                if (fl3.visibility == View.GONE) {
                    fl3.visibility = View.VISIBLE
                } else {
                    fl3.visibility = View.GONE
                }


            }

            R.id.rb4 -> {
                appBarLayout.setExpanded(false)

                fl1.visibility = View.GONE
                fl3.visibility = View.GONE

                if (fl4.visibility == View.GONE) {
                    fl4.visibility = View.VISIBLE
                } else {
                    fl4.visibility = View.GONE
                }



                if (list4Display != null && list4Display?.size!! > 0) {

                    list4Display?.forEach { it ->
                        it.nodes.forEach {
                            it.check = it.confirmCheck
                        }
                    }
                    query4Adapter?.list = list4Display
                    query4Adapter?.notifyDataSetChanged()
                } else {
                    query4Adapter?.list?.forEachIndexed { indexX, nodesBeanXXX ->

                        nodesBeanXXX.nodes.forEachIndexed { indexY, nodesBeanXX ->
                            nodesBeanXX.check = false
                        }

                    }

                    query4Adapter?.notifyDataSetChanged()
                    rb4.text = "筛选(${list4Select.size})"
                }


            }


            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            R.id.tv_reset -> {

                if (list4Display != null) {
                    list4Display?.forEach { it ->
                        it.nodes.forEach {
                            it.check = false
                            it.confirmCheck = false
                        }
                    }
                }


                list4Select.clear()
                query4Adapter?.list?.forEachIndexed { indexX, nodesBeanXXX ->

                    nodesBeanXXX.nodes.forEachIndexed { indexY, nodesBeanXX ->
                        nodesBeanXX.check = false
                    }

                }

                query4Adapter?.notifyDataSetChanged()
                rb4.text = "筛选(${list4Select.size})"
            }

            R.id.tv_confirm -> {


                if (query4Adapter != null && query4Adapter?.list != null) {


                    list4Select.clear()
                    query4Adapter?.list?.forEachIndexed { indexX, nodesBeanXXX ->
                        nodesBeanXXX.nodes.forEachIndexed { indexY, nodesBeanXX ->
                            if (nodesBeanXX.check) {
                                nodesBeanXX.confirmCheck = true
                                list4Select.add(nodesBeanXX)
                            } else {
                                nodesBeanXX.confirmCheck = false
                            }
                        }

                    }
                    fl4.visibility = View.GONE
                    list4Display = ArrayList()
                    list4Display?.addAll(query4Adapter!!.list)
                    rb4.text = "筛选(${list4Select.size})"

                }
            }


            R.id.fl1 -> {
                fl1.visibility = View.GONE
            }

            R.id.fl3 -> {
                fl3.visibility = View.GONE
            }

            R.id.fl4 -> {
                fl4.visibility = View.GONE
            }


            else -> {

                val postcard = ARouter.getInstance().build("/SearchActivity/Search")
                LogisticsCenter.completion(postcard);
                val destination = postcard.destination
                val intent = Intent(getContext(), destination)
                startActivityForResult(intent, 10086)

            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.restaurantinfragment

    var present: MeiShiPresent? = null

    var query1Adapter: Query1Adapter? = null
    var query3Adapter: Query1Adapter? = null
    var query4Adapter: Query4Adapter? = null


    val listAll = ArrayList<Query1Bean>()
    val list3 = ArrayList<Query1Bean>()

    val list4 = ArrayList<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX>()


    var list4Display: ArrayList<MeiShiHeadBean.QuerySearchBean.NodesBeanXXX>? = null


    val listFoot = ArrayList<MeiShiFootBean.ItemsBean>()

    var mAdapter: RestaurantInAdapter? = null

    val pageSize = 15
    var pageIndex = 0


    var queryPresent: QueryModelDataPresent? = null

    override fun initView() {
        ImmersionBar.with(mActivity)
            .statusBarDarkFont(true, 0.2f)
            .init()


        val type = arguments?.getString("type")
        val typeValue = arguments?.getString("typeValue")

        if ("MORE" == type) {
            ll_typeA.visibility = View.GONE
            mcv_Search.visibility = View.GONE
            tv_title.visibility = View.VISIBLE
            tv_title.text = typeValue
            iv_search.visibility = View.VISIBLE
            mcv_Search_type.visibility = View.VISIBLE

            appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                    if (state == State.EXPANDED) {

                        //展开状态
                        iv_search.visibility = View.GONE

                    } else if (state == State.COLLAPSED) {
                        //折叠状态

                        iv_search.visibility = View.VISIBLE
                    } else {
                        //中间状态
                        iv_search.visibility = View.GONE

                    }
                }
            });

        } else {
            mcv_Search_type.visibility = View.GONE
            iv_search.visibility = View.GONE
        }


        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mAdapter = RestaurantInAdapter(listFoot)
        recyclerView.adapter = mAdapter
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(RestaurantHomeFragment.newInstance((adapter.data[position] as MeiShiFootBean.ItemsBean).id))
        }

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, recyclerView)



        present = MeiShiPresent(mActivity, this)
        present?.module("")

        queryPresent = QueryModelDataPresent(mActivity, this)


        queryPresent?.meishiData("", "", "", "", "", "", "", pageIndex, pageSize, "refresh")

        val arrays = arrayOf(rb1, rb2, rb3, rb4, iv_back, tv_reset, tv_confirm, fl1, fl3, fl4)

        query1Adapter = Query1Adapter(mActivity, listAll, 0)
        lv_1.adapter = query1Adapter
        lv_1.setOnItemClickListener { _, _, position, _ ->
            fl1.visibility = View.GONE
            rb1.text = listAll[position].title
            lv_1.adapter = Query1Adapter(mActivity, listAll, position)

            pageIndex = 0


        }


        query3Adapter = Query1Adapter(mActivity, list3, 0)
        lv_3.adapter = query3Adapter
        lv_3.setOnItemClickListener { parent, view, position, id ->
            fl3.visibility = View.GONE
            rb3.text = list3[position].title
            lv_3.adapter = Query1Adapter(mActivity, list3, position)

            pageIndex = 0

        }


        query4Adapter = Query4Adapter(mActivity, list4)
        lv_4.adapter = query4Adapter



        arrays.forEach {
            it.setOnClickListener(this)
        }


    }


    private fun loadMore() {

        pageIndex++
        queryPresent?.meishiData("", "", "", "", "", "", "", pageIndex, pageSize, "loadMore")

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        when (requestCode) {
            10086 -> {
                val keyWord = data?.getStringExtra("keyWord")
                Toast.makeText(mActivity, keyWord, Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }

    }


    companion object {
        fun newInstance(type: String?, typeValue: String?): RestaurantInFragment {
            val fragment = RestaurantInFragment()
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putString("typeValue", typeValue)

            fragment.arguments = bundle
            return fragment
        }
    }

}
