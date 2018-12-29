package com.paipaiwei.personal.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.paipaiwei.personal.R
import com.paipaiwei.personal.adapter.MoreSectionAdapter
import com.paipaiwei.personal.common.SpacesItemDecoration
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.SpaceItemDecoration
import com.yjhh.common.utils.SpaceItemDecoration2
import kotlinx.android.synthetic.main.activity_more_section.*

class MoreSectionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_section)


        val list = ArrayList<String>()
        list.add("AA")
        list.add("AA")
        list.add("AA")
        list.add("AA")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(SpaceItemDecoration2(20, "top"))
        recyclerView.adapter = MoreSectionAdapter(this,list)
    }
}
