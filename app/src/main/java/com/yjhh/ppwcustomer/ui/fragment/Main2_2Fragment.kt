package com.yjhh.ppwcustomer.ui.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.PullToRefreshAdapter

import kotlinx.android.synthetic.main.main2_2fragment.*

class Main2_2Fragment :BaseFragment() {


    override fun initView() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutRes(): Int  = R.layout.main2_2fragment

    override fun initData() {
        Log.i("TAG","Main2_2Fragment")


        val  mAdapter = PullToRefreshAdapter()

        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)

        mRecyclerView.adapter = mAdapter

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_LONG).show()
            }
        })

    }


    fun getData():ArrayList<String>{
        val list= ArrayList<String>()
        list.add("A")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        list.add("B")
        return list
    }

}