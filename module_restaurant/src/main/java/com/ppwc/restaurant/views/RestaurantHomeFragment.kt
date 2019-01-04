package com.ppwc.restaurant.views

import android.Manifest
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

import com.flyco.tablayout.listener.CustomTabEntity

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_FIXED
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.RestaurantHomeAdapter
import com.ppwc.restaurant.ipresent.ShopPresent
import com.ppwc.restaurant.iview.RestaurantView
import com.ppwc.restaurant.mrbean.MultipleItem
import com.ppwc.restaurant.mrbean.RestaurantHomeBean
import com.ppwc.restaurant.mrlistener.MuIListener
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.common.BaseApplication

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.TabEntity
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.PhoneUtils
import com.yjhh.common.utils.ShareUtils
import com.yjhh.common.view.AlertDialogFactory
import kotlinx.android.synthetic.main.restauranthomefragment.*


class RestaurantHomeFragment : BaseFragment(), View.OnClickListener, RestaurantView {
    override fun onRestaurantValue(model: RestaurantHomeBean) {

        if (tv_image_text != null)
            tv_image_text.text = model.imageText

        ImageLoaderUtils.load(
            BaseApplication.getIns(),
            iv_image,
            model.logoUrl,
            R.drawable.icon_place_pai,
            R.drawable.icon_place_pai,
            5
        )

        tv_storeName.text = model.name


        if (model.times != null && model.times.size > 0) {
            val sb = StringBuilder()

            model.times.forEach {
                sb.append(it.begin).append("-").append(it.end).append(" ")
            }

            tv_times.text = "${sb.toString()} 营业"
        }

        tv_renjun.text = "${model.renj}/每人"



        iv_tel.setOnClickListener {


            val disposable = RxPermissions(this)
                .request(Manifest.permission.CALL_PHONE)
                .subscribe {
                    if (it) {

                        AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                            "拨打商家电话",
                            model.tel,
                            "确定", "取消",
                            { dlg, v ->
                                PhoneUtils.callPhone(mActivity, model.tel)
                            }, { dlg, v ->
                            })


                    } else {
                        Toast.makeText(mActivity, "请前往设置中心开启拨打电话", Toast.LENGTH_SHORT).show()
                    }
                }

            compositeDisposable.add(disposable)

        }







        if (model.oneMoney != null && model.oneMoney.size > 0) {
            listValue.add(MultipleItem(MultipleItem.A, false, model.oneMoney))
        }

        if (model.products != null && model.products.size > 0) {
            listValue.add(MultipleItem(MultipleItem.B, model.products, model.productCount))
        }

        if (model.userComment != null && model.userComment.size > 0) {
            listValue.add(MultipleItem(MultipleItem.C, model.userComment, model.userCommentCount))
        }

        listValue.add(MultipleItem(MultipleItem.D))


        mAdapter?.notifyDataSetChanged()

    }

    override fun onFault(errorMsg: String?) {


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_image -> {
                start(RestaurantAlbumFragment())
            }

            R.id.iv_back -> {
                mActivity.onBackPressed()
            }


            R.id.iv_share -> {
                ShareUtils.getInstance().initWx(mActivity).sendToWeiXin(1)
            }

            R.id.tv_buy -> {
                start(MRCheckPayFragment())
            }

            R.id.tv_buy2 -> {
                start(MRCheckPayFragment())
            }

            R.id.iv_like -> {
                iv_like.setBackgroundResource(R.drawable.xingxing02)


            }


            else -> {
            }
        }
    }


    private val titles1 = arrayOf("一元拍", "店铺推荐", "用户评价")

    override fun getLayoutRes(): Int = R.layout.restauranthomefragment


    val listValue = ArrayList<MultipleItem>()


    var mAdapter: RestaurantHomeAdapter? = null

    override fun initView() {


        for (i in 0 until titles1.size) {

            mTabLayout_7.addTab(mTabLayout_7.newTab().setText(titles1[i]))
        }

        mTabLayout_7.tabMode = MODE_FIXED

        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        val listValue = listValue

        mAdapter = RestaurantHomeAdapter(listValue, recyclerView, childFragmentManager)

        recyclerView.adapter = mAdapter
        ShopPresent(mActivity, this).shop("1009")












        iv_like.setBackgroundResource(R.drawable.icon_unlike)


        mTabLayout_7.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //点击tab的时候，RecyclerView自动滑到该tab对应的item位置
                if (tab.position != -1) {  //切换RecyclerView位置
                    recyclerView.scrollToPosition(tab.position)
                    val mLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    mLayoutManager.scrollToPositionWithOffset(tab.position, 0)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        val mOnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                mTabLayout_7.setScrollPosition(
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(),
                    0f,
                    true
                )

            }


        }



        recyclerView.addOnScrollListener(mOnScrollListener)



        mAdapter?.setMUIOnClickListener(object : MuIListener {
            override fun tv_more2() {

                start(RecommendProductFragment())
            }

            override fun tv_more3() {

            }

            override fun tv_if(position: Int) {
                //Toast.makeText(mActivity, "position:tv_if$position", Toast.LENGTH_SHORT).show()


                if (listValue[0].flag!!) {

                    listValue[0].flag = false


                    mAdapter?.listAExpandableLayout?.get(position)?.collapse()

                    val drawableLeft = resources.getDrawable(
                        R.drawable.icon_down_black
                    )

                    mAdapter?.listATextView?.get(position)?.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null, drawableLeft, null
                    );
                    mAdapter?.listATextView?.get(position)?.compoundDrawablePadding = 4


                    //mAdapter.notifyItemChanged(position)
                } else {
                    listValue[0].flag = true
                    mAdapter?.listAExpandableLayout?.get(position)?.expand()


                    val drawableLeft = resources.getDrawable(
                        R.drawable.icon_up_black
                    )
                    mAdapter?.listATextView?.get(position)?.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null, drawableLeft, null
                    );
                    mAdapter?.listATextView?.get(position)?.compoundDrawablePadding = 4
                }


            }

            override fun tv_pai(position: Int) {
                // Toast.makeText(mActivity, "position:tv_pai$position", Toast.LENGTH_SHORT).show()
            }

        })







        arrayOf(iv_image, iv_back, iv_like, iv_share, tv_buy, tv_buy2).forEach {
            it.setOnClickListener(this)
        }


    }


}