package com.paipaiwei.personal.ui.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.paipaiwei.personal.R
import com.yjhh.common.base.BaseActivity
import io.flutter.app.FlutterActivity
import io.flutter.facade.Flutter


class FlutterDisActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter_dis)

        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.frameLayout, Flutter.createFragment("route1"))
        tx.commit()

    }
}
