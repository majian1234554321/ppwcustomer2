package com.yjhh.ppwcustomer.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.yjhh.ppwcustomer.R

class MainActivity : AppCompatActivity() {

    lateinit var iv :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv =   findViewById(R.id.iv)

    }
}
