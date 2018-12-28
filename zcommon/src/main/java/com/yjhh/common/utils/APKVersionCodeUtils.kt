package com.yjhh.common.utils

import android.app.Activity
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.text.TextUtils
import com.azhon.appupdate.config.UpdateConfiguration
import com.azhon.appupdate.listener.OnDownloadListener
import com.azhon.appupdate.manager.DownloadManager
import com.yjhh.common.BaseApplication
import com.yjhh.common.R


object APKVersionCodeUtils {


    fun isAvailable(mapPackageName: String, activity: Activity): Boolean {
        var packageInfo: PackageInfo?
        try {
            packageInfo = activity.packageManager.getPackageInfo(mapPackageName, 0);
        } catch (e: PackageManager.NameNotFoundException) {
            packageInfo = null
            e.printStackTrace()
        }
        return packageInfo != null
    }


    fun getVersionCode(mContext: Context): Int {
        var versionCode = 0
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.packageManager.getPackageInfo(mContext.packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return versionCode
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    fun getVerName(context: Context): String {
        var verName = ""
        try {
            verName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return verName
    }

    /**
     * 获取渠道名
     *
     * @param ctx 此处习惯性的设置为activity，实际上context就可以
     * @return 如果没有获取成功，那么返回值为空
     */
    fun getChannelName(): String {
        var channelName: String? = "company"
        try {
            val packageManager = BaseApplication.getIns().packageManager
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                val applicationInfo = packageManager.getApplicationInfo(
                    BaseApplication.getIns().packageName,
                    PackageManager.GET_META_DATA
                )
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL")
                    }
                }

            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        if (!TextUtils.isEmpty(channelName)) {
            channelName = "company"
        }

        return channelName.toString()
    }

    fun startUpdate(ctx: Context, onDownloadListener: OnDownloadListener) {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        val configuration = UpdateConfiguration()
            //输出错误日志
            .setEnableLog(true)
            //设置自定义的下载
            //.setHttpManager()
            //下载完成自动跳动安装页面
            .setJumpInstallPage(true)
            //设置对话框背景图片 (图片规范参照demo中的示例图)
            //.setDialogImage(R.drawable.ic_dialog)
            //支持断点下载
            .setBreakpointDownload(true)
            //设置是否显示通知栏进度
            .setShowNotification(true)
            //设置下载过程的监听
            .setOnDownloadListener(onDownloadListener)

        val manager = DownloadManager.getInstance(ctx)

        manager.setApkName("appupdate.apk")
            .setApkUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
            // .setSmallIcon(R.mipmap.ic_launcher)
            .setShowNewerToast(true)
            .setConfiguration(configuration)
            .setAuthorities(ctx.packageName)
            .setApkDescription("1.支持断点下载\n2.支持Android N\n3.支持Android O\n4.支持自定义下载过程\n5.支持 设备>=Android M 动态权限的申请\n6.支持通知栏进度条展示(或者自定义显示进度)")
            .download()
    }

}


