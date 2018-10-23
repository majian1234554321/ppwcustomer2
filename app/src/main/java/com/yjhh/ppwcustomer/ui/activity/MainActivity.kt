package com.yjhh.ppwcustomer.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.ActivityCollector
import com.yjhh.loginmodule.ui.LoginActivity
import com.yjhh.ppwcustomer.R
import kotlinx.android.synthetic.main.activity_main.*


@Route(path = "/mainActivity/main")
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rb1.setOnClickListener {
          //  Toast.makeText(this,"0",Toast.LENGTH_SHORT).show()



           // ActivityCollector.JumpActivity(this,LoginActivity::class.java)


            ARouter.getInstance()
                .build("/LoginActivity/Login")
                .withString("name", "老王")
                .withInt("age", 23)
                .navigation(this)

        }
    }
}
