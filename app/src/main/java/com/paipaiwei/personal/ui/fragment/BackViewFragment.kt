package com.paipaiwei.personal.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.gyf.barlibrary.ImmersionBar
import com.yjhh.common.BaseApplication
import com.yjhh.common.base.BaseFragment
import com.yjhh.common.utils.APKVersionCodeUtils
import com.yjhh.common.utils.SharedPreferencesUtils
import com.paipaiwei.personal.R
import com.paipaiwei.personal.interfaces.MyJSInterface
import kotlinx.android.synthetic.main.backviewfragment.*


@SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface")
class BackViewFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.backviewfragment

    override fun initView() {
        val url = arguments?.getString("url")
        val type = arguments?.getString("type")

        if (!TextUtils.isEmpty(type)) {
            ImmersionBar.setTitleBar(mActivity, rl)
        }

        mWebView.clearHistory()
        mWebView.clearCache(true)

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        webSettings.allowFileAccess = true// 设置允许访问文件数据 v

        webSettings.setSupportZoom(false)

        webSettings.builtInZoomControls = false

        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true

        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        webSettings.databaseEnabled = true
        webSettings.useWideViewPort = true

        mWebView .setInitialScale(25)


        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    progressBar1.visibility = View.GONE
                } else {
                    progressBar1.visibility = View.VISIBLE
                    progressBar1.progress = newProgress
                }

            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)

                if (!TextUtils.isEmpty(title) && tv_title != null) {
                    tv_title.text = title
                }
            }
        }



        val map = HashMap<String, String?>()
        map.put("User-Agent", WebSettings.getDefaultUserAgent(BaseApplication.context) + "PPW_App")
        map.put("userAgent", "PPW_App")

        map.put("PPW-TERMINAL", "0") //（0 用户端 1商户端)
        map.put(
            "PPW-APP-VERSION",
            APKVersionCodeUtils.getVersionCode(
                BaseApplication.context

            ).toString()
        )
        //.header("PPW-SIGN", "XMLHttpRequest")
        map.put("PPW-TIMESTAMP", (System.currentTimeMillis() / 1000).toInt().toString())
        map.put("PPW-API-VERSION", "1.0")
        map.put("PPW-MARKET-ID", APKVersionCodeUtils.getChannelName())

        map.put("PPW-DEVICE-ID", APKVersionCodeUtils.getChannelName())

        map.put(
            "JSESSIONID",
            SharedPreferencesUtils.getParam(BaseApplication.context, "sessionId", "-1").toString()
        )
        webSettings.userAgentString = WebSettings.getDefaultUserAgent(BaseApplication.context) + "PPW_App"

        mWebView.loadUrl(url, map)


        // mWebView.loadUrl("file:////android_asset/a.html")


        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                //  mWebView.loadUrl("javascript:show(0)")
            }
        }

        mWebView.addJavascriptInterface(MyJSInterface(mActivity), "android")


        iv_back.setOnClickListener {
            if (mWebView.canGoBack()) {
                mWebView.goBack()
            } else {
                mActivity.onBackPressed()
            }

        }

        mWebView.setOnKeyListener { v, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {

                mWebView.goBack()

                true
            } else
                false

        }


    }


    companion object {
        fun newInstance(url: String?): BackViewFragment {
            val fragment = BackViewFragment()
            val bundle = Bundle()

            bundle.putString("url", url)

            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(url: String?, type: String?): BackViewFragment {
            val fragment = BackViewFragment()
            val bundle = Bundle()

            bundle.putString("url", url)
            bundle.putString("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

}













