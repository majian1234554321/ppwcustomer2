package com.yjhh.ppwcustomer.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.base.BaseActivity
import com.yjhh.ppwcustomer.bean.LoginBean
import com.yjhh.ppwcustomer.present.LoginPresent
import com.yjhh.ppwcustomer.view.LoginView

class LoginActivity : BaseActivity(), LoginView {
    override fun onSuccess(result: LoginBean?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val loginPresent = LoginPresent(this, this)
        loginPresent.login("A", "B")


    }
}
