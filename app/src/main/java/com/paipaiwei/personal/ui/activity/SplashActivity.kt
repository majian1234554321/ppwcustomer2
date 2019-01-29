package com.paipaiwei.personal.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.azhon.appupdate.listener.OnDownloadListener
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.common.present.CommonPresent
import com.yjhh.common.iview.CommonView
import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.VersionBean
import com.paipaiwei.personal.ui.activity.login.LoginActivity
import com.paipaiwei.personal.ui.customview.AppUpdateFragment
import com.yjhh.common.utils.*

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.Subject
import java.io.File
import java.lang.Exception

@Route(path = "/splashactivity/splash")
class SplashActivity : AppCompatActivity(), CommonView {


    var dialog: AppUpdateFragment? = null


    internal var onDownloadListener: OnDownloadListener = object : OnDownloadListener {
        override fun start() {

        }

        override fun downloading(max: Int, progress: Int) {
            Log.i("MainActivity", "下载进度${progress / max.toDouble()}%${Thread.currentThread().name}")
            val dis = Observable.create<String> {
                it.onNext(
                    "下载进度 ${getString(
                        R.string.rmb_price_double,
                        progress / max.toDouble() * 100
                    )}%"
                )

            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dialog?.setTitle(it)
                }
        }

        override fun done(apk: File) {
            Log.i("MainActivity", "下载完成")

            val dis = Observable.create<String> {
                it.onNext("下载完成")
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dialog?.setTitle(it)
                }

        }

        override fun error(e: Exception) {

        }
    }

    var permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS
    )
    private var tips: TextView? = null
    private var disposable: Disposable? = null
    private var rxPermissions: RxPermissions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tips = findViewById(R.id.text)


        val present = CommonPresent(this, this)
        present.init()

        rxPermissions = RxPermissions(this)

        present.checkVersion()
        disposable = rxPermissions!!.request(*permissions).subscribe { present.checkVersion() }

    }


    override fun onDestroy() {
        super.onDestroy()
        if (disposable != null) {
            disposable!!.dispose()
        }


    }

    protected fun setStatusBar() {

        val decorView = window.decorView
        decorView.setBackgroundResource(R.mipmap.timg)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
        //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }


    override fun onSuccess(value: String?, flag: String?) {
        val modelVersionBean = Gson().fromJson<VersionBean>(value, VersionBean::class.java)
        if (APKVersionCodeUtils.getVerName(this) != modelVersionBean.version) {
            dialog = if (modelVersionBean.ifCover == 1) {//是否强制覆盖(0否 1是)
                AppUpdateFragment(true, modelVersionBean.content, modelVersionBean.marketUrl)
            } else {
                AppUpdateFragment(false, modelVersionBean.content, modelVersionBean.marketUrl)
            }
            dialog?.show(supportFragmentManager, "TAG")
            dialog?.setOnAppUpdate(object : AppUpdateFragment.AppUpdateListener {
                override fun close() {


                    if (!TextUtils.isEmpty(
                            SharedPreferencesUtils.getParam(
                                this@SplashActivity,
                                "sessionId",
                                ""
                            ) as String
                        )
                    ) {
                        ActivityCollector.JumpActivity(this@SplashActivity, MainActivity::class.java)
                    } else {

                        ActivityCollector.JumpActivity(this@SplashActivity, LoginActivity::class.java)
                    }

                    dialog?.dismiss()
                    finish()

                }

                override fun onAppUpdate() {
                    APKVersionCodeUtils.startUpdate(
                        this@SplashActivity,
                        modelVersionBean.downloadUrl,
                        onDownloadListener
                    )
                }

            })

        } else {

            RxCountDown.countdown(3)
                .subscribe(object : DisposableObserver<Int>() {
                    override fun onNext(t: Int) {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                        if (!TextUtils.isEmpty(
                                SharedPreferencesUtils.getParam(
                                    this@SplashActivity,
                                    "sessionId",
                                    ""
                                ) as String
                            )
                        ) {
                            ActivityCollector.JumpActivity(this@SplashActivity, MainActivity::class.java)
                        } else {

                            ActivityCollector.JumpActivity(this@SplashActivity, LoginActivity::class.java)
                        }

                        finish()
                    }
                })

        }
    }

    override fun onFault(errorMsg: String?, flag: String?) {
        ActivityCollector.JumpActivity(this@SplashActivity, MainActivity::class.java)
        finish()
    }
}
