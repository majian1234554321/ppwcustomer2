package com.yjhh.ppwcustomer.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.ColorUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.Main1FragmentAdapter
import com.yjhh.ppwcustomer.bean.Main1FootBean
import com.yjhh.ppwcustomer.bean.MainFinalDataBean
import com.yjhh.ppwcustomer.common.utils.GlideImageLoader

import com.yjhh.ppwcustomer.present.SectionMain1Present
import com.yjhh.ppwcustomer.view.Main1View
import kotlinx.android.synthetic.main.main1fragment.*
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.uuzuche.lib_zxing.activity.CaptureActivity
import com.yjhh.common.Constants
import com.yjhh.ppwcustomer.adapter.PullToRefreshAdapter
import com.yjhh.ppwcustomer.adapter.SearchAdapter
import com.yjhh.ppwcustomer.bean.Main1HeadBean
import com.yjhh.ppwcustomer.ui.activity.*
import com.yjhh.ppwcustomer.ui.activity.takeout.FoodActivity
import com.yjhh.ppwcustomer.ui.customview.GridViewPager
import com.youth.banner.Banner

import kotlinx.android.synthetic.main.main1title.*


class Main1Fragment : BaseFragment(), Main1View, View.OnClickListener {


    var startindex = 0
    val pageSize = 10

    override fun getLayoutRes(): Int = R.layout.main1fragment

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_location -> {
//                ARouter.getInstance()
//                    .build("/DisplayActivity/Display")
//                    .withString("displayTab", "SelectDistrictFragment")
//                    .withInt("age", 23)
//                    .navigation(mActivity,10085)


                val intent =  Intent(context, DisplayActivity::class.java)

                intent.putExtra("displayTab","SelectDistrictFragment")

                this@Main1Fragment.startActivityForResult(intent, 10085)



            }

            R.id.tv_search -> {
               startActivity(Intent(context, SearchActivity::class.java))
            }

            R.id.iv_scan -> {
               val intent =  Intent(context, CaptureActivity::class.java)

                this@Main1Fragment.startActivityForResult(intent, 10086)

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
        mRecyclerView.layoutManager = LinearLayoutManager(context)
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
            refreshLayout.finishRefresh()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        when (requestCode) {
            10086 -> {
                if (data != null) {

                    val  content = data?.getStringExtra("result_string");
                    tv_search.text = content
                }
            }
            else -> {

              val location =  data?.getStringExtra("location")
                tv_search.text = location
            }
        }
    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)


        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()

                when (position) {
                    0 -> {
                        startActivity(Intent(mActivity, MoreSectionActivity::class.java))
                    }
                    1 -> {
                        startActivity(Intent(mActivity, FoodActivity::class.java))
                    }
                    else -> {
                    }
                }
            }
        })
    }


    var banner: Banner? = null

    var mGridViewPager: GridViewPager? = null

    private fun addHeaderView() {


        val headView: View = layoutInflater.inflate(R.layout.mainhead, mRecyclerView.parent as ViewGroup, false)


        banner = headView.findViewById<Banner>(R.id.banner)

        mGridViewPager = headView.findViewById(R.id.mGridViewPager)

        mAdapter.addHeaderView(headView)

//        val bannerParams = banner!!.getLayoutParams()
//        val titleBarParams = toolbar.getLayoutParams()
//        bannerHeight = bannerParams.height - titleBarParams.height

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

                    }
                    .setGridItemLongClickListener { pos, position, str ->

                    }
                    .init(list)
            }


        } else {

        }



        if ("refresh" == flag) {
            Log.i("TAG", Constants.district)
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
        Toast.makeText(context, "12", Toast.LENGTH_SHORT).show()
        startindex++
        sectionMain1Present.joinMain(startindex, pageSize, "load")
    }


}