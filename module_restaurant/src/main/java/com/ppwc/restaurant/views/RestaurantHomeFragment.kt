package com.ppwc.restaurant.views

import android.util.Log
import android.view.View
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
import com.ppwc.restaurant.mrbean.MultipleItem
import com.ppwc.restaurant.mrlistener.MuIListener

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.TabEntity
import com.yjhh.common.utils.ShareUtils
import kotlinx.android.synthetic.main.restauranthomefragment.*
import net.cachapa.expandablelayout.ExpandableLayout


class RestaurantHomeFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_image -> {
                start(RestaurantAlbumFragment())
            }

            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            R.id.iv_like -> {

            }

            R.id.iv_share -> {
                ShareUtils.getInstance().initWx(mActivity).sendToWeiXin(1)
            }

            else -> {
            }
        }
    }


    private val titles1 = arrayOf("一元拍", "店铺推荐", "用户评价")
    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()
    override fun getLayoutRes(): Int = R.layout.restauranthomefragment

    override fun initView() {


        for (i in 0 until titles1.size) {
            mTabEntities.add(TabEntity(titles1[i], R.drawable.drop_down_shadow, R.drawable.drop_down_shadow))


            mTabLayout_7.addTab(mTabLayout_7.newTab().setText(titles1[i]))
        }

        mTabLayout_7.tabMode = MODE_FIXED

        recyclerView.layoutManager = LinearLayoutManager(mActivity)

        val listValue = getMultipleItemData()

        val mAdapter = RestaurantHomeAdapter(listValue, recyclerView, childFragmentManager)

        recyclerView.adapter = mAdapter




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



        mAdapter.setMUIOnClickListener(object : MuIListener {
            override fun tv_if(position: Int) {
                //Toast.makeText(mActivity, "position:tv_if$position", Toast.LENGTH_SHORT).show()


                if (listValue[0].flag!!) {

                    listValue[0].flag = false


                    mAdapter.listAExpandableLayout.get(position).collapse()

                    val drawableLeft = resources.getDrawable(
                        R.drawable.icon_down_black
                    )

                    mAdapter.listATextView.get(position).setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null, drawableLeft, null
                    );
                    mAdapter.listATextView.get(position).compoundDrawablePadding = 4


                    //mAdapter.notifyItemChanged(position)
                } else {
                    listValue[0].flag = true
                    mAdapter.listAExpandableLayout.get(position).expand()


                    val drawableLeft = resources.getDrawable(
                        R.drawable.icon_up_black
                    )
                    mAdapter.listATextView.get(position).setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null, drawableLeft, null
                    );
                    mAdapter.listATextView.get(position).compoundDrawablePadding = 4
                }


            }

            override fun tv_pai(position: Int) {
                // Toast.makeText(mActivity, "position:tv_pai$position", Toast.LENGTH_SHORT).show()
            }

        })







        arrayOf(iv_image, iv_back, iv_like, iv_share).forEach {
            it.setOnClickListener(this)
        }


    }

    fun getMultipleItemData(): List<MultipleItem> {
        val list = ArrayList<MultipleItem>()

        val listString = ArrayList<String>()

        for (i in 1..3) {
            listString.add(i.toString())
        }


        list.add(MultipleItem(MultipleItem.A, false, listString))

        list.add(MultipleItem(MultipleItem.B, 1, listString))

        list.add(MultipleItem(MultipleItem.C, 2, listString))

        list.add(MultipleItem(MultipleItem.D, -1))




        return list
    }


}