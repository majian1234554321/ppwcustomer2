package com.ppwc.restaurant.views

import androidx.recyclerview.widget.GridLayoutManager
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.RestaurantAlbumAdapter
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.view.fragments.PhotoFragment
import kotlinx.android.synthetic.main.restaurantalbumfragment.*

class RestaurantAlbumFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.restaurantalbumfragment

    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)


        val list = ArrayList<String>()

        for (i in 1..15) {
            list.add("我的是等分胜多负少发送仿发放")
        }


        val mAdapter = RestaurantAlbumAdapter(list)

        recyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener { adapter, view, position ->
            val dialog = PhotoFragment(list, position)
            dialog?.show(childFragmentManager, "TAG")
        }
    }
}