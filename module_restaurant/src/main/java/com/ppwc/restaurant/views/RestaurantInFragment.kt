package com.ppwc.restaurant.views

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.ppwc.restaurant.R
import com.ppwc.restaurant.adapter.FoodLVAdapter2
import com.ppwc.restaurant.adapter.RestaurantInAdapter
import com.yjhh.common.base.BaseFragment
import kotlinx.android.synthetic.main.restaurantinfragment.*

class RestaurantInFragment : BaseFragment(), View.OnClickListener {
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


            R.id.iv_back -> {
                mActivity.onBackPressed()
            }


            else -> {

                val postcard = ARouter.getInstance().build("/SearchActivity/Search")
                LogisticsCenter.completion(postcard);
                val destination = postcard.destination


                val intent = Intent(getContext(), destination)
                startActivityForResult(intent, 10086)

            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.restaurantinfragment


    override fun initView() {


        val list = ArrayList<String>()
        list.add("智能排序")
        list.add("好评优先")
        list.add("销量最高")
        list.add("配送费低到高")
        list.add("配送费高到低")
        list.add("人均从低到高")
        list.add("人均从高到低")


        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)


        val mAdapter = RestaurantInAdapter(list)

        recyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener { adapter, view, position ->


            start(RestaurantHomeFragment())
        }


        val arrays = arrayOf(rb1, rb2, rb3, rb4, fl_select, lv_FL, mcv_Search, iv_back)
        lv_sort.adapter = FoodLVAdapter2(mActivity, list, 0)




        lv_sort.setOnItemClickListener { parent, view, position, id ->
            lv_FL.visibility = View.GONE
            rb1.text = list[position]
            lv_sort.adapter = FoodLVAdapter2(mActivity, list, position)


        }


        arrays.forEach {
            it.setOnClickListener(this)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        when (requestCode) {
            10086 -> {

                val keyWord = data?.getStringExtra("keyWord")
                Toast.makeText(mActivity, keyWord, Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }

    }


    companion object {
        fun newInstance(): RestaurantInFragment {
            return RestaurantInFragment()
        }
    }

}
