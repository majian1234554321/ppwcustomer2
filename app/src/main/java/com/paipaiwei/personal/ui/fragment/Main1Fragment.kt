package com.paipaiwei.personal.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main1FragmentAdapter
import com.paipaiwei.personal.bean.MainFinalDataBean
import com.paipaiwei.personal.common.utils.GlideImageLoader

import com.paipaiwei.personal.present.SectionMain1Present
import com.paipaiwei.personal.view.Main1View
import kotlinx.android.synthetic.main.main1fragment.*
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.yjhh.common.Constants
import com.paipaiwei.personal.bean.Main1HeadBean
import com.paipaiwei.personal.ui.activity.*
import com.paipaiwei.personal.ui.activity.parishfood.BusinessHomeActivity
import com.paipaiwei.personal.ui.activity.takeout.FoodActivity
import com.paipaiwei.personal.ui.customview.GridViewPager
import com.youth.banner.Banner

import kotlinx.android.synthetic.main.main1title.*


class Main1Fragment : BaseMainFragment(), Main1View, View.OnClickListener {


    var startindex = 0
    val pageSize = 10

    override fun getLayoutRes(): Int = R.layout.main1fragment

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_location -> {
                val intent = Intent(context, DisplayActivity::class.java)
                intent.putExtra("displayTab", "SelectDistrictFragment")
                this@Main1Fragment.startActivityForResult(intent, 10085)
            }

            R.id.tv_search -> {
                startActivity(Intent(context, SearchActivity::class.java))
            }

            R.id.iv_scan -> {
//                val intent = Intent(context, CaptureActivity::class.java)
//
//                this@Main1Fragment.startActivityForResult(intent, 10086)

                startActivity(Intent(mActivity, SelectMapActivity::class.java))
                // startActivity(Intent(mActivity, BusinessHomeActivity::class.java))

            }
            else -> {
            }
        }
    }


    var mAdapter: Main1FragmentAdapter = Main1FragmentAdapter()
    lateinit var sectionMain1Present: SectionMain1Present
    override fun initView() {

        tv_location.text = Constants.district
        sectionMain1Present = SectionMain1Present(context, this)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()
        addHeaderView()
        mAdapter.setPreLoadNumber(1)
        tv_location.setOnClickListener(this)
        tv_search.setOnClickListener(this)
        iv_scan.setOnClickListener(this)
    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh("refresh")

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        when (requestCode) {
            10086 -> {
                if (data != null) {

                    val content = data.getStringExtra("result_string")
                    tv_search.text = content
                }
            }
            else -> {

                val location = data?.getStringExtra("location")
                tv_search.text = location
            }
        }
    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)



        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()

                /* when (position) {
                     0 -> {
                         startActivity(Intent(mActivity, MoreSectionActivity::class.java))
                     }
                     1 -> {
                         startActivity(Intent(mActivity, FoodActivity::class.java))
                     }
                     else -> {
                     }
                 }*/
            }
        })
    }


    var banner: Banner? = null

    var mGridViewPager: GridViewPager? = null

    private fun addHeaderView() {


        val headView: View = layoutInflater.inflate(R.layout.mainhead, mRecyclerView.parent as ViewGroup, false)


        banner = headView.findViewById(R.id.banner)

        mGridViewPager = headView.findViewById(R.id.mGridViewPager)

        mAdapter.addHeaderView(headView)


    }

    var bannerHeight = 0


    override fun onSuccess(main1bean: MainFinalDataBean, flag: String) {


        if (main1bean.main1HeadBean != null) {
            val bannerImage = ArrayList<String>()
            main1bean.main1HeadBean.banners?.forEach {
                // bannerImage.add(it.imageUrl)
            }
            banner!!.setImages(bannerImage)
                .setImageLoader(GlideImageLoader())
                .setDelayTime(5000)
                .start()


            val list = ArrayList<Main1HeadBean.TabsBean>()
            list.clear()
            if (main1bean.main1HeadBean.tabs != null) {
                main1bean.main1HeadBean.tabs?.forEach {
                    list.add(it)
                }
            }

            if (list.size > 0) {
                mGridViewPager!!
                    .setPageSize(10)
                    .setGridItemClickListener { pos, position, str ->

                        Toast.makeText(
                            mActivity,
                            "pos$pos:position$position:str$str",
                            Toast.LENGTH_SHORT
                        ).show()


                        when (position) {
                            0 -> {




                                ARouter.getInstance()
                                    .build("/RestaurantActivity/Restaurant")
                                    .withString("displayTab", "RestaurantInFragment")
                                    // .withInt("age", 23)
                                    .navigation()

                            }

                            1 -> {
                                startActivity(Intent(mActivity, BusinessHomeActivity::class.java))
                            }

                            2 -> {
                            }

                            else -> {
                                startActivity(Intent(mActivity, MoreSectionActivity::class.java))
                            }
                        }


                    }
                    .setGridItemLongClickListener { pos, position, str ->

                    }
                    .init(list)
            }


        } else {

        }



        if ("refresh" == flag) {
            Log.i("TAG", Constants.district)
            swipeLayout.finishRefresh()
            mAdapter.setNewData(main1bean.main1FootBean.items)

        } else {
            mAdapter.addData(main1bean.main1FootBean.items)
            mAdapter.loadMoreComplete()
        }


    }

    override fun onFault(errorMsg: String?) {

    }


    private fun refresh(flag: String) {
        startindex = 0
        sectionMain1Present.joinMain(startindex, pageSize, flag)
    }

    private fun loadMore() {
        startindex++
        sectionMain1Present.joinMain(startindex, pageSize, "load")
    }


}