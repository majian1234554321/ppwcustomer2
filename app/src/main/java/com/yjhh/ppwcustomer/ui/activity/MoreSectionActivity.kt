package com.yjhh.ppwcustomer.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.adapter.MoreSectionAdapter
import kotlinx.android.synthetic.main.activity_more_section.*

class MoreSectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_section)


        val list = ArrayList<String>()
        list.add("AA")
        list.add("AA")
        list.add("AA")
        list.add("AA")



//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = MoreSectionAdapter(this,list)
    }
}
