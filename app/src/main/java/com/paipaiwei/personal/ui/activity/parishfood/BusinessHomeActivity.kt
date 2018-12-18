package com.paipaiwei.personal.ui.activity.parishfood

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Main2ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_business_home.*

class BusinessHomeActivity : BaseActivity(), View.OnClickListener {

    val arrays = arrayOf("商家推荐", "用户评价", "商家信息")

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_buy -> {
                startActivity(Intent(this, ParishBuyActivity::class.java))
            }

            R.id.tv_Reserve -> {

                startActivity(Intent(this, ParishReserveActivity::class.java))
            }

            else -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_home)
        id_ratingbar.setStar(4.5f)


        val arrayView = arrayOf(tv_buy, tv_Reserve)

        arrayView.forEach {
            it.setOnClickListener(this)
        }




        viewPager.currentItem = 0

        val lists = ArrayList<BaseFragment>()
        lists.add(RecommendHomeFragment())
        lists.add(EvaluationHomeFragment())
        lists.add(InformationHomeFragment())


        viewPager.adapter = Main2ViewPagerAdapter(supportFragmentManager, lists, arrays)
        mTabLayout.setViewPager(viewPager)



    }
}
