package com.yjhh.ppwcustomer.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_business_home.*

class BusinessHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_home)
        id_ratingbar.setStar(4.5f)
    }
}
