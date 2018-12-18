package com.paipaiwei.personal.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.yjhh.common.base.BaseActivity
import com.yjhh.common.utils.ActivityCollector

import com.paipaiwei.personal.CurrentApplication
import com.paipaiwei.personal.R


import com.paipaiwei.personal.ui.fragment.*
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator
import java.util.*
import kotlin.collections.ArrayList


@Route(path = "/mainActivity/main")
class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }
    }

    override fun onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        // 设置横向(和安卓4.x动画相同)
        return DefaultHorizontalAnimator()
    }



//    private var firstTime: Long = 0L
//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
//            val secondTime = System.currentTimeMillis()
//            if (secondTime - firstTime > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
//                firstTime = secondTime
//                return true
//            } else {
//                finish()
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }


}
