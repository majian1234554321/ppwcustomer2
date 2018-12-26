package com.ppwc.restaurant.views

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.RestaurantHomeAdapter
import com.ppwc.restaurant.mrbean.MultipleItem

import com.yjhh.common.base.BaseFragment
import com.yjhh.common.bean.TabEntity
import kotlinx.android.synthetic.main.restauranthomefragment.*


class RestaurantHomeFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_image -> {
                start(RestaurantAlbumFragment())
            }

            R.id.iv_back ->{
                mActivity.onBackPressed()
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
            mTabLayout_7.setTabData(mTabEntities)
        }

        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        val mAdapter = RestaurantHomeAdapter(getMultipleItemData())

        recyclerView.adapter = mAdapter



        mTabLayout_7.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {

                if (position != -1) {  //切换RecyclerView位置
                    recyclerView.scrollToPosition(position)
                    val mLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    mLayoutManager.scrollToPositionWithOffset(position, 0)
                }
            }

            override fun onTabReselect(position: Int) {

            }

        })


        val mOnScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as LinearLayoutManager?
                //获取当前显示的Item位置
                val nowPosition = manager!!.findFirstVisibleItemPosition()

                //使TabLayout切换到指定位置
                mTabLayout_7.currentTab = nowPosition
            }
        }




        recyclerView.addOnScrollListener(mOnScrollListener)





        arrayOf(iv_image,iv_back).forEach {
            it.setOnClickListener(this)
        }







        mAdapter.setOnItemChildClickListener { adapter, view, position ->

            when (view.id) {
                R.id.tv_useIf -> {
                    Toast.makeText(mActivity, "tv_useIf", Toast.LENGTH_SHORT).show()
                }

                R.id.tv_more2 -> {
                    Toast.makeText(mActivity, "tv_more2", Toast.LENGTH_SHORT).show()

                    start(RecommendProductFragment())

                }

                R.id.tv_pai -> {
                    Toast.makeText(mActivity, "tv_pai", Toast.LENGTH_SHORT).show()
                }


                else -> {

                }
            }
        }


    }


    fun getMultipleItemData(): List<MultipleItem> {
        val list = ArrayList<MultipleItem>()
        for (i in 0..4) {
            list.add(MultipleItem(MultipleItem.A, i))


        }


        for (i in 0..4) {
            list.add(MultipleItem(MultipleItem.B, i * 4))

        }







        return list
    }


}