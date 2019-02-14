package com.paipaiwei.personal.ui.fragment

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.azhon.appupdate.listener.OnDownloadListener
import com.google.gson.Gson
import com.yjhh.common.base.BaseFragment

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.bean.VersionBean
import com.paipaiwei.personal.common.utils.DataCleanManager
import com.paipaiwei.personal.ui.activity.MainActivity
import com.paipaiwei.personal.ui.customview.AppUpdateFragment
import com.yjhh.common.iview.CommonView
import com.yjhh.common.present.CommonPresent
import com.yjhh.common.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.settingfragment.*
import java.io.File
import java.lang.Exception

class SettingFragment : BaseFragment(), View.OnClickListener, CommonView {




    override fun onSuccess(value: String?, flag: String?) {
        val modelVersionBean = Gson().fromJson<VersionBean>(value, VersionBean::class.java)


        if (APKVersionCodeUtils.getVerName(mActivity) != modelVersionBean.version) {

            if (!TextUtils.isEmpty(modelVersionBean.marketUrl)){


                dialog = if (modelVersionBean.ifCover == 1) {//是否强制覆盖(0否 1是)
                    AppUpdateFragment(true, modelVersionBean.content, modelVersionBean.marketUrl)
                } else {
                    AppUpdateFragment(false, modelVersionBean.content, modelVersionBean.marketUrl)
                }
                dialog?.show(fragmentManager, "TAG")
                dialog?.setOnAppUpdate(object : AppUpdateFragment.AppUpdateListener {
                    override fun close() {


                        ActivityCollector.JumpActivity(mActivity, MainActivity::class.java)
                        dialog?.dismiss()


                    }

                    override fun onAppUpdate() {
                        APKVersionCodeUtils.startUpdate(
                            mActivity,
                            modelVersionBean.downloadUrl,
                            onDownloadListener
                        )
                    }

                })}else{
                ActivityCollector.JumpActivity(mActivity, MainActivity::class.java)



            }

        } else {

            RxCountDown.countdown(3)
                .subscribe(object : DisposableObserver<Int>() {
                    override fun onNext(t: Int) {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                        ActivityCollector.JumpActivity(mActivity, MainActivity::class.java)


                    }
                })

        }
    }

    override fun onFault(errorMsg: String?, flag: String?) {
        ActivityCollector.JumpActivity(mActivity, MainActivity::class.java)

    }





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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev_resetPwd -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "ResetPasswordFragment")
                    .withInt("age", 23)
                    .navigation()

            }
            R.id.iev_resetPhone -> {


                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "ChangeMobileFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.iev_resetMessage -> {

            }

            R.id.iev_resetMemory -> {
                DataCleanManager.clearAllCache(mActivity)
                iev_resetMemory.setTextContent(DataCleanManager.getTotalCacheSize(mActivity))
            }

            R.id.iev_resetVersion -> {


                val present = CommonPresent(mActivity, this)
                present.checkVersion()


            }

            R.id.tv_loginout -> {
                loginOut()


                ARouter.getInstance()
                    .build("/LoginActivity/Login")

                    .navigation(context)

                RxBus.default.post(LoginBean("", false))

                activity?.finish()

            }

            else -> {
            }
        }
    }

    override fun initView() {


        iev_resetMemory.setTextContent(DataCleanManager.getTotalCacheSize(mActivity))


        arrayOf(
            iev_resetPwd,
            iev_resetPhone,
            iev_resetMessage,
            iev_resetMemory,
            iev_resetVersion,
            tv_loginout
        ).forEach {
            it.setOnClickListener(this)
        }

        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
            tv_loginout.visibility = View.VISIBLE
        } else {
            tv_loginout.visibility = View.GONE
        }


    }

    override fun getLayoutRes(): Int = R.layout.settingfragment


}