package com.paipaiwei.takeout_personal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.paipaiwei.takeout_personal.R
import com.paipaiwei.takeout_personal.adapter.MoreSectionAdapter
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
