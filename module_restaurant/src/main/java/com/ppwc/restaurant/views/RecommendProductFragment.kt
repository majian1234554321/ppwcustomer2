package com.ppwc.restaurant.views

import androidx.recyclerview.widget.LinearLayoutManager
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.RecommendProductAdapter
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.recommendproductfragment.*

class RecommendProductFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.recommendproductfragment


    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        val list = ArrayList<String>();

        for (i in 1..10) {
            list.add("$i")
        }


        var mAdapter = RecommendProductAdapter(list)

        recyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener { adapter, view, position ->
            start(ProductDetailsFragment())
        }


    }

}