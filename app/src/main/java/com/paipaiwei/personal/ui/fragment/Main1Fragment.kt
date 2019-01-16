package com.paipaiwei.personal.ui.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.button.MaterialButton
import com.gyf.barlibrary.ImmersionBar
import com.paipaiwei.personal.R
import com.paipaiwei.personal.R.id.mb_pai


import com.paipaiwei.personal.bean.Main1FootBean
import com.paipaiwei.personal.bean.Main1HeadBean
import com.paipaiwei.personal.bean.MainFinalDataBean
import com.paipaiwei.personal.common.utils.GlideImageLoader
import com.paipaiwei.personal.present.SectionMain1Present
import com.paipaiwei.personal.ui.activity.DisplayActivity
import com.paipaiwei.personal.ui.activity.MoreSectionActivity
import com.paipaiwei.personal.ui.activity.SearchActivity
import com.paipaiwei.personal.ui.activity.parishfood.BusinessHomeActivity
import com.paipaiwei.personal.ui.customview.GridViewPager
import com.paipaiwei.personal.view.Main1View
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.uuzuche.lib_zxing.activity.CaptureActivity
import com.yjhh.common.BaseApplication
import com.yjhh.common.Constants
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.RxCountDown
import com.yjhh.common.utils.TimeUtil
import com.yjhh.common.view.RatingBar
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.main1fragment.*
import kotlinx.android.synthetic.main.main1title.*
import java.lang.StringBuilder


class Main1Fragment : BaseMainFragment(), Main1View, View.OnClickListener {


    var startindex = 0
    val pageSize = 15


    override fun onSuccess(main1bean: MainFinalDataBean, flag: String) {


        if (main1bean.main1HeadBean != null) {
            val bannerImage = ArrayList<String>()
            main1bean.main1HeadBean.banners?.forEach {
                bannerImage.add(it.imageUrl)
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



            if (main1bean.main1HeadBean.qiangPais != null) {
                hRecyclerView?.adapter = Main1HeadAdapter(main1bean.main1HeadBean.qiangPais)
            }



            if (list.size > 0) {
                mGridViewPager!!
                    .setPageSize(10)
                    .setGridItemClickListener { pos, position, str ->

                        when (position) {
                            0 -> {
                                ARouter.getInstance()
                                    .build("/RestaurantActivity/Restaurant")
                                    .withString("displayTab", "RestaurantInFragment")
                                    .navigation()

                            }

                            1 -> {
                                startActivity(Intent(mActivity, BusinessHomeActivity::class.java))
                            }

                            2 -> {
                            }

                            else -> {

                            }
                        }


                    }
                    .setGridItemLongClickListener { pos, position, str ->

                    }
                    .init(list)
            }


        } else {
        }


        if (main1bean.main1FootBean != null) {
            if ("refresh" == flag) {
                mAdapter?.setNewData(main1bean.main1FootBean.items)
                if (startindex == 0) {

                    if (main1bean.main1FootBean.items.size == pageSize) {

                    } else {
                        mAdapter?.loadMoreEnd()
                    }

                } else {

                }


            } else {
                if (main1bean.main1FootBean.items != null && main1bean.main1FootBean.items.size == pageSize) {
                    mAdapter?.loadMoreComplete()
                } else {
                    mAdapter?.loadMoreEnd()
                }

                mAdapter?.addData(main1bean.main1FootBean.items)

            }
        }


    }


    override fun onFault(errorMsg: String?) {
        Toast.makeText(mActivity, errorMsg, Toast.LENGTH_SHORT).show()
    }


    override fun getLayoutRes(): Int = R.layout.main1fragment


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_location -> {
                val intent = Intent(context, DisplayActivity::class.java)
                intent.putExtra("displayTab", "SelectDistrictFragment")
                intent.putExtra("value1", tv_location.text.toString().trim())
                this@Main1Fragment.startActivityForResult(intent, 10085)
            }

            R.id.tv_search -> {
                startActivity(Intent(context, SearchActivity::class.java))
            }

            R.id.iv_scan -> {
                val intent = Intent(context, CaptureActivity::class.java)
                this@Main1Fragment.startActivityForResult(intent, 10086)


            }

            R.id.iv_fenlei ->{
                startActivity(Intent(mActivity, MoreSectionActivity::class.java))
            }

            R.id.iv_imagegame->{
                (parentFragment as MainFragment).startBrotherFragment(QiangPaiFragment())
            }

            else -> {
            }
        }
    }


    var sectionMain1Present: SectionMain1Present? = null

    var mAdapter: Main1FooterAdapter? = null

    override fun initView() {

        ImmersionBar.setTitleBar(activity, title)

        val list = ArrayList<Main1FootBean.ItemsBean>()
        mAdapter = Main1FooterAdapter(list)

        tv_location.text = Constants.district
        sectionMain1Present = SectionMain1Present(context, this)


        initAdapter()
        initRefreshLayout()
        addHeaderView()

        arrayOf(tv_search, iv_scan, tv_location,iv_fenlei).forEach {
            it.setOnClickListener(this)
        }

    }

    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(mActivity))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            swipeLayout.finishRefresh()
        }
        swipeLayout.autoRefresh()

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
                if (!TextUtils.isEmpty(location))
                    tv_location.text = location
            }
        }
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(mActivity)

        recyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, recyclerView)

        mAdapter?.setOnItemClickListener { adapter, view, position ->


            ARouter.getInstance()
                .build("/RestaurantActivity/Restaurant")
                .withString("displayTab", "RestaurantHomeFragment")
                .withString("id", (adapter.data[position] as Main1FootBean.ItemsBean).id)
                .navigation()

        }

    }

    var banner: Banner? = null
    var mGridViewPager: GridViewPager? = null
    var hRecyclerView: RecyclerView? = null

    private fun addHeaderView() {


        val headView: View = layoutInflater.inflate(R.layout.mainhead, recyclerView.parent as ViewGroup, false)
        banner = headView.findViewById(R.id.banner)
        mGridViewPager = headView.findViewById(R.id.mGridViewPager)
        mGridViewPager?.setVis()
        hRecyclerView = headView.findViewById(R.id.hRecyclerView)
       val  iv_imagegame = headView.findViewById<ImageView>(R.id.iv_imagegame)
        iv_imagegame.setOnClickListener(this)
        hRecyclerView?.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 3)

        mAdapter?.addHeaderView(headView)


    }

    private fun refresh() {
        startindex = 0
        sectionMain1Present?.joinMain(startindex, pageSize, "refresh")
    }

    private fun loadMore() {
        startindex++
        sectionMain1Present?.joinMain(startindex, pageSize, "load")
    }


    class Main1HeadAdapter(data: List<Main1HeadBean.QiangPaisBean>) :
        BaseQuickAdapter<Main1HeadBean.QiangPaisBean, BaseViewHolder>(R.layout.main1headadapter, data) {
        override fun convert(helper: BaseViewHolder?, item: Main1HeadBean.QiangPaisBean?) {


            helper?.setText(R.id.tv_name, item?.title)?.setText(R.id.mb_pai, item?.statusText)


            val mbpai = helper?.getView<TextView>(R.id.mb_pai)

            val dis = RxCountDown.countdown(item!!.time).subscribe {
                Log.i("Main1Fragment", it.toString())
                helper?.setVisible(R.id.tv_count, it != 0)
                helper?.setText(R.id.tv_count, "剩余  ${TimeUtil.secondToTime(it.toLong())}")
            }


            when (item.status) {
                0 -> {// 0即将开始 1进行中 2已结束/已拍完
                    mbpai?.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                    mbpai?.setBackgroundResource(R.drawable.checked_red)
                }
                1 -> {// 0即将开始 1进行中 2已结束/已拍完
                    mbpai?.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                    mbpai?.setBackgroundResource(R.drawable.checked_red)
                }
                2 -> {// 0即将开始 1进行中 2已结束/已拍完
                    mbpai?.setBackgroundResource(R.drawable.checked_gray)
                    mbpai?.setTextColor(Color.parseColor("#FFB5B5B5"))
                }
                else -> {
                }
            }






            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                helper?.getView(R.id.iv_image_head),
                item?.imageUrl,
                com.ppwc.restaurant.R.drawable.icon_place_pai,
                com.ppwc.restaurant.R.drawable.icon_place_pai,
                5
            )


        }
    }


    class Main1FooterAdapter(data: List<Main1FootBean.ItemsBean>) :
        BaseQuickAdapter<Main1FootBean.ItemsBean, BaseViewHolder>(R.layout.main1footeradapter, data) {
        override fun convert(helper: BaseViewHolder?, item: Main1FootBean.ItemsBean?) {


            helper?.setText(com.ppwc.restaurant.R.id.tv_storeName, item?.name)
            helper?.setText(com.ppwc.restaurant.R.id.tv_avgPrice, "${item?.perCapita}/元")

            val sb = StringBuilder()
            item?.labels?.forEach {
                sb.append(it).append("\t")
            }
            helper?.setText(com.ppwc.restaurant.R.id.tv_info, sb.toString())

            helper?.setText(com.ppwc.restaurant.R.id.tv_KM, item?.distance)

            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                helper?.getView(com.ppwc.restaurant.R.id.iv_image),
                item?.logoUrl,
                com.ppwc.restaurant.R.drawable.icon_place_pai,
                com.ppwc.restaurant.R.drawable.icon_place_pai,
                5
            )



            item?.grade?.toFloat()
                ?.let { helper?.getView<RatingBar>(com.ppwc.restaurant.R.id.id_ratingbar)?.setStar(it) }

            helper?.setText(com.ppwc.restaurant.R.id.tv_score, item?.grade.toString())


            if (item?.ifNews!!) {
                helper?.setVisible(com.ppwc.restaurant.R.id.iv_zsj, true)
                helper?.setText(com.ppwc.restaurant.R.id.iv_zsj, "新店")
            } else if (item?.ifRec) {
                helper?.setVisible(com.ppwc.restaurant.R.id.iv_zsj, true)
                helper?.setText(com.ppwc.restaurant.R.id.iv_zsj, "推荐")
            } else {
                helper?.setVisible(com.ppwc.restaurant.R.id.iv_zsj, false)
            }





            if (item?.coupons != null) {
                if (item?.coupons.size >= 1) {
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.text = item?.coupons[0].valueText
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.visibility = View.VISIBLE
                } else {
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.visibility = View.GONE
                }

                if (item?.coupons.size >= 2) {
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.text = item?.coupons[1].valueText
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.visibility = View.VISIBLE
                } else {
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.visibility = View.GONE
                }

                if (item?.coupons.size >= 3) {
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.text = item?.coupons[2].valueText
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.visibility = View.VISIBLE
                    helper?.setVisible(com.ppwc.restaurant.R.id.tv_more, true)

                } else {
                    helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.visibility = View.GONE

                    helper?.setVisible(com.ppwc.restaurant.R.id.tv_more, false)
                }


            } else {
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_1)?.visibility = View.GONE
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_2)?.visibility = View.GONE
                helper?.getView<TextView>(com.ppwc.restaurant.R.id.tv_3)?.visibility = View.GONE
            }


        }
    }

}