package com.yjhh.ppwcustomer.ui.activity.takeout

import android.content.Intent
import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.recyclerview.widget.LinearLayoutManager

import android.view.View
import android.widget.Toast
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.FoodAdapter
import com.yjhh.ppwcustomer.adapter.FoodLVAdapter
import com.yjhh.ppwcustomer.ui.activity.ActivityELMe
import com.yjhh.ppwcustomer.ui.activity.SearchActivity

import kotlinx.android.synthetic.main.activity_food.*


class FoodActivity : BaseActivity(), View.OnClickListener {


    private val mTitles = arrayOf("热门", "iOS")

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rb1 -> {
                fl_select.visibility = View.GONE
                if (lv_FL.visibility == View.GONE) {
                    lv_FL.visibility = View.VISIBLE
                } else {
                    lv_FL.visibility = View.GONE
                }
            }

            R.id.rb2 -> {
                lv_FL.visibility = View.GONE
                fl_select.visibility = View.GONE
            }

            R.id.rb3 -> {
                lv_FL.visibility = View.GONE
                fl_select.visibility = View.GONE
            }

            R.id.rb4 -> {
                lv_FL.visibility = View.GONE
                if (fl_select.visibility == View.GONE) {
                    fl_select.visibility = View.VISIBLE
                } else {
                    fl_select.visibility = View.GONE
                }

            }

            R.id.lv_FL -> {
                lv_FL.visibility = View.GONE
            }

            R.id.fl_select -> {
                lv_FL.visibility = View.GONE
                if (fl_select.visibility == View.GONE) {
                    fl_select.visibility = View.VISIBLE
                } else {
                    fl_select.visibility = View.GONE
                }
            }

            R.id.tv_search ->{
                startActivity(Intent(this,SearchActivity::class.java))
            }

            else -> {
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        val list = ArrayList<String>()
        list.add("智能排序")
        list.add("好评优先")
        list.add("销量最高")
        list.add("配送费低到高")
        list.add("配送费高到低")
        list.add("人均从低到高")
        list.add("人均从高到低")


        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        val mAdapter = FoodAdapter(list)

        recyclerView.adapter = mAdapter






        mTitles.forEach {
            mTabLayout.addTab(it)
        }





        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })


        val arrays = arrayOf(rb1, rb2, rb3, rb4, fl_select, lv_FL,tv_search)
        lv_sort.adapter = FoodLVAdapter(this, list, 0)




        lv_sort.setOnItemClickListener { parent, view, position, id ->
            lv_FL.visibility = View.GONE
            rb1.text = list[position]
            lv_sort.adapter = FoodLVAdapter(this, list, position)


        }


        arrays.forEach {
            it.setOnClickListener(this)
        }


        mAdapter.setOnItemClickListener { adapter, view, position ->
            startActivity(Intent(this, ActivityELMe::class.java))
        }

    }

}
