package com.paipaiwei.personal.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhon.appupdate.utils.DensityUtil
import com.google.android.material.tabs.TabLayout
import com.yjhh.common.base.BaseFragment
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.Collection123Adapter
import com.paipaiwei.personal.adapter.Main2Adapter

import kotlinx.android.synthetic.main.main2fragment.*
import kotlinx.android.synthetic.main.main2fragment.view.*
import java.lang.StringBuilder

class Main2Fragment : BaseMainFragment() {

    private val titles1 = arrayOf("全部", "美食", "休闲娱乐", "全部", "美食", "全部", "美食", "全部", "美食", "全部")


    private val titlesid = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    override fun getLayoutRes(): Int = R.layout.main2fragment

    override fun initView() {

        val lisCheckBox = ArrayList<CheckBox>()

        for (i in 0 until titles1.size) {


            val checkboxitem = View.inflate(mActivity, R.layout.checkboxitem, null) as CheckBox
            checkboxitem.buttonDrawable = ColorDrawable(Color.TRANSPARENT)
            checkboxitem.setBackgroundResource(R.drawable.uncheckbox)
            checkboxitem.setTextColor(Color.parseColor("#666666"))
            checkboxitem.text = titles1[i]
            checkboxitem.isChecked = false


            val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.rightMargin = 12

            ll.addView(checkboxitem, lp)
            lisCheckBox.add(checkboxitem)

            mTabLayout1.addTab(mTabLayout1.newTab().setText(titles1[i]))


        }

        lisCheckBox.forEachIndexed { index, checkBox ->

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (checkBox.isChecked) {
                    buttonView.setBackgroundResource(R.drawable.checkbox)
                    buttonView.setTextColor(Color.WHITE)
                } else {
                    buttonView.setBackgroundResource(R.drawable.uncheckbox)
                    buttonView.setTextColor(Color.parseColor("#666666"))
                }

            }
        }

        recyclerView.layoutManager = LinearLayoutManager(mActivity)


       val  mAdapter =  Main2Adapter(titles1.toList())

        recyclerView.adapter = mAdapter

        mTabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {


                val sb = StringBuilder()

                lisCheckBox.forEachIndexed { index, checkBox ->
                    if (checkBox.isChecked) {
                        sb.append(index)
                    }
                }


                Toast.makeText(mActivity, sb.toString(), Toast.LENGTH_SHORT).show()
            }

        })

    }


}