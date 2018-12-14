package com.paipaiwei.takeout_personal.ui.activity.login


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


import com.yjhh.common.base.BaseActivity

import com.paipaiwei.takeout_personal.R


import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator




@Route(path = "/LoginActivity/Login")
class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)  // Start auto inject.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (findFragment(LoginFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, LoginFragment.newInstance())
        }
    }

    override fun onBackPressedSupport() {

        super.onBackPressedSupport()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {

        return DefaultHorizontalAnimator()
    }


}



