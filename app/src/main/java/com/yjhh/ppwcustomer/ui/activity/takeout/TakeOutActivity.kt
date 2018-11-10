package com.yjhh.ppwcustomer.ui.activity.takeout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_take_out.*

class TakeOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_out)

        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}
