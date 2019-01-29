package com.ppwc.restaurant.views

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.RestaurantAlbumAdapter
import com.ppwc.restaurant.ipresent.ShopPresent
import com.ppwc.restaurant.iview.RestaurantAlbumView
import com.ppwc.restaurant.iview.RestaurantView
import com.ppwc.restaurant.mrbean.RestaurantAlbumBean
import com.ppwc.restaurant.mrbean.RestaurantHomeBean
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.view.fragments.PhotoFragment
import kotlinx.android.synthetic.main.restaurantalbumfragment.*

class RestaurantAlbumFragment : BaseFragment(), RestaurantAlbumView {
    override fun onRestaurantAlbumValue(model: RestaurantAlbumBean) {
        mAdapter?.setNewData(model.items)


        model.items.forEach {
            listString.add(it.imageUrl)
        }
    }

    override fun onFault(errorMsg: String?) {

    }


    override fun getLayoutRes(): Int = R.layout.restaurantalbumfragment


    var pageIndex = 0
    val pageSize = 15
    var mAdapter: RestaurantAlbumAdapter? = null
    val list = ArrayList<RestaurantAlbumBean.ItemsBean>()
    val listString = ArrayList<String>()

    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)





        val shopId = arguments?.getString("id")



        ShopPresent(mActivity, this).images(shopId, "", pageIndex, pageSize)


        mAdapter = RestaurantAlbumAdapter(list)
        recyclerView.adapter = mAdapter


        mAdapter?.setOnItemClickListener { adapter, view, position ->

            val dialog = PhotoFragment(listString, position)
            dialog?.show(childFragmentManager, "TAG")
        }
    }





    companion object {
        fun newInstance(id: String?): RestaurantAlbumFragment {
            val fragment = RestaurantAlbumFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }


}