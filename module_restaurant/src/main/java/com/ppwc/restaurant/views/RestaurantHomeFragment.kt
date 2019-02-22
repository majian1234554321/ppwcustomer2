package com.ppwc.restaurant.views

import android.Manifest
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.alibaba.android.arouter.launcher.ARouter

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
import com.tencent.connect.common.Constants
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import com.yjhh.common.BaseApplication

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.TabEntity
import com.yjhh.common.iview.CommonView
import com.yjhh.common.present.CommonPresent
import com.yjhh.common.utils.ImageLoaderUtils
import com.yjhh.common.utils.PhoneUtils
import com.yjhh.common.utils.ShareUtils
import com.yjhh.common.utils.TextStyleUtils
import com.yjhh.common.view.AlertDialogFactory
import kotlinx.android.synthetic.main.restauranthomefragment.*


class RestaurantHomeFragment : BaseFragment(), View.OnClickListener, RestaurantView, CommonView {
    override fun onFault(errorMsg: String?, flag: String?) {

    }

    override fun onSuccess(value: String?, flag: String?) {
        if ("collect" == flag) {
            if (ifCollect) {
                iv_like.setBackgroundResource(R.drawable.icon_unlike)
            } else {
                iv_like.setBackgroundResource(R.drawable.xingxing02)
            }

            ifCollect = !ifCollect
        }
    }


    var ifCollect = false

    var shopName :String? = null

    override fun onRestaurantValue(model: RestaurantHomeBean) {

        if (tv_image_text != null)
            tv_image_text.text = model.imageText

        if (iv_image != null) {
            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                iv_image,
                model.logoUrl,
                R.drawable.icon_place_square,
                R.drawable.icon_place_square,
                5
            )
        }

        tv_storeName?.text = model.name
        tv_address?.text = model.address

        shopName = model.name;

        ratingbar?.setStar(model.grade)


        tv_XX?.text = model.grade.toInt().toString()

        if (model.times != null && model.times.size > 0) {
            val sb = StringBuilder()

            model.times.forEach {
                sb.append(it.begin).append("-").append(it.end).append(" ")
            }

            tv_times?.text = "${sb.toString()} 营业"
        }



        ifCollect = model.ifCollect
        if (model.ifCollect) {
            iv_like?.setBackgroundResource(R.drawable.xingxing02)
        } else {
            iv_like?.setBackgroundResource(R.drawable.icon_unlike)
        }


        tv_renjun?.text = "${model.renj}"


        if (model.qiangPai != null && model.qiangPai.size > 0) {
            ll_qiangpai.visibility = View.VISIBLE
            tv_store_buy.text = model.qiangPai[0].shopName


            val price1 = "￥${model.qiangPai[0].costPrice}"
            tv_price1.text = TextStyleUtils.changeTextAa(price1, 0, 1, 10)
            tv_price2.text = "￥${model.qiangPai[0].markPrice}"
            tv_price2.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

            tv_tips.text = model.qiangPai[0].countText

            ImageLoaderUtils.load(
                BaseApplication.getIns(),
                iv_image_buy,
                model.qiangPai.get(0).imageUrl,
                R.drawable.icon_place_square,
                R.drawable.icon_place_square,
                5
            )


            tv_buy.setOnClickListener {

                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "BuyOvervalueDetailsFragment")
                    .withString("value", model.qiangPai[0].id)
                    .withString("value1", "商家首页超值抢购")
                    .navigation()


            }


        } else {
            ll_qiangpai.visibility = View.GONE
        }


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
            titles1.add("一元拍")

            listValue.add(MultipleItem(MultipleItem.A, false, model.oneMoney))
        }

        if (model.products != null && model.products.size > 0) {

            titles1.add("店铺推荐")

            listValue.add(MultipleItem(MultipleItem.B, model.products, model.productCount))
        }

        if (model.userComment != null && model.userComment.size > 0) {
            titles1.add("用户评价")
            listValue.add(MultipleItem(MultipleItem.C, model.userComment, model.userCommentCount, model.commentLabel))
        }

        listValue.add(MultipleItem(MultipleItem.D))


        for (i in 0 until titles1.size) {

            mTabLayout_7.addTab(mTabLayout_7.newTab().setText(titles1[i]))
        }



        mAdapter?.notifyDataSetChanged()

    }

    override fun onFault(errorMsg: String?) {


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_image -> {
                start(RestaurantAlbumFragment.newInstance(shopId))
            }

            R.id.iv_back -> {
                mActivity.onBackPressed()
            }


            R.id.iv_share -> {

                ShareUtils.dialog(mActivity, mIUiListener, "", "", "")
            }


            R.id.tv_buy2 -> {
                start(RestaurantHomePayFragment.newInstance("", "", shopId, "1"))


            }

            R.id.iv_like -> {
                // iv_like.setBackgroundResource(R.drawable.xingxing02)

                CommonPresent(mActivity, this).collect("1", shopId, "collect")

            }


            else -> {


                start(ParishReserveFragment.newInstance(shopId, shopName))


            }
        }
    }

    // arrayOf("一元拍", "店铺推荐", "用户评价")
    private val titles1 = ArrayList<String>()
    var mIUiListener: MyIUiListener? = null

    override fun getLayoutRes(): Int = R.layout.restauranthomefragment

    var shopId: String? = null

    val listValue = ArrayList<MultipleItem>()


    var mAdapter: RestaurantHomeAdapter? = null

    var present: ShopPresent? = null

    override fun initView() {
        shopId = arguments?.getString("id")
        mIUiListener = MyIUiListener()

        present = ShopPresent(mActivity, this)


        /**
         * itemType  类别(0商品 1店铺 2文章)
         *
         * itemId  商品id/店铺id/文章Id
         */


        mTabLayout_7.tabMode = MODE_FIXED

        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        val listValue = listValue

        mAdapter = RestaurantHomeAdapter(listValue, recyclerView, this, childFragmentManager)


        recyclerView.adapter = mAdapter



        present?.shop(shopId)



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


                if (mTabLayout_7 != null) {
                    mTabLayout_7.setScrollPosition(
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition(),
                        0f,
                        true
                    )
                }
            }


        }

        recyclerView.addOnScrollListener(mOnScrollListener)



        mAdapter?.setMUIOnClickListener(object : MuIListener {
            override fun tv_more2() {

                start(RecommendProductFragment.newInstance(shopId))
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


                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "BackViewFragment")
                    .withString("value", listValue[0].listone?.get(position)?.paiLinkUrl)
                    .navigation()


            }

        })







        arrayOf(iv_image, iv_back, iv_like, iv_share, tv_buy1, tv_buy2).forEach {
            it.setOnClickListener(this)
        }


    }


    class MyIUiListener : IUiListener {
        override fun onComplete(p0: Any?) {

        }

        override fun onCancel() {

        }

        override fun onError(p0: UiError?) {

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }
    }


    companion object {
        fun newInstance(id: String?): RestaurantHomeFragment {

            val fragment = RestaurantHomeFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment


        }
    }


}