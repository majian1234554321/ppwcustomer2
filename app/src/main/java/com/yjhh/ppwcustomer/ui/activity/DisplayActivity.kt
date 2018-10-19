package com.yjhh.ppwcustomer.ui.activity

import android.os.Bundle
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.base.BaseActivity
import com.yjhh.ppwcustomer.base.BaseFragment
import com.yjhh.ppwcustomer.ui.fragment.LoginFragment

class DisplayActivity : BaseActivity() {


    private lateinit var fragments: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val displayTab = intent.getStringExtra("displayTab")

        when (displayTab) {
            "" -> {
                fragments = LoginFragment()
            }

            "" -> {
                fragments = LoginFragment()
            }
            "" -> {
                fragments = LoginFragment()
            }
            "" -> {
                fragments = LoginFragment()
            }
            "" -> {
                fragments = LoginFragment()
            }


            else -> {

            }
        }


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragments).commit()


    }
}
