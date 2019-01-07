package com.paipaiwei.personal.ui.fragment


import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter

import com.yjhh.common.utils.LogUtils
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.ui.activity.FlutterDisActivity
import com.paipaiwei.personal.ui.activity.SignActivity
import com.paipaiwei.personal.ui.activity.UserInfoActivity
import com.paipaiwei.personal.ui.activity.evaluate.EvaluateManageFragment
import com.paipaiwei.personal.ui.activity.onepay.OnePayFragment
import com.yjhh.common.utils.ImageLoaderUtils

import kotlinx.android.synthetic.main.main4fragment.*


class Main4Fragment : BaseMainFragment(), View.OnClickListener {


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.iev_about -> {
               // (parentFragment as MainFragment).startBrotherFragment(AboutFragment())

                (parentFragment as MainFragment).startBrotherFragment(OnePayFragment())
                //startActivity(Intent(mActivity, FlutterDisActivity::class.java))

            }

            R.id.iev_browse -> {
//                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
//                    ARouter.getInstance()
//                        .build("/DisplayActivity/Display")
//                        .withString("displayTab", "RecentlyBrowseFragment")
//                        .withInt("age", 23)
//                        .navigation(context)
//                } else {
//
//                }


                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {


                    (parentFragment as MainFragment).startBrotherFragment(CollectionFragment.newInstance("浏览"))

                } else {

                }


            }

            R.id.iv_message -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                    (parentFragment as MainFragment).startBrotherFragment(MessageCenterFragment())
                } else {

                }
            }

            R.id.iev_Collection -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {


                    (parentFragment as MainFragment).startBrotherFragment(CollectionFragment.newInstance("收藏"))

                } else {

                }

            }

            R.id.ll_Integral -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "IntegralFragment")
                        .withInt("age", 23)
                        .navigation(context)
                } else {

                }

            }

//            R.id.tv_Coupon -> {
//                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
//                    ARouter.getInstance()
//                        .build("/DisplayActivity/Display")
//                        .withString("displayTab", "CouponFragment")
//                        .withInt("age", 23)
//                        .navigation(context)
//                } else {
//
//                }
//
//            }

            R.id.iv_setting -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "SettingFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.tv_name -> {

                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    startActivity(Intent(mActivity, UserInfoActivity::class.java))
                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")
                        .withString("name", "老王")
                        .withInt("age", 23)
                        .navigation(context)
                }


            }

            R.id.rl0 -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    startActivity(Intent(mActivity, UserInfoActivity::class.java))

                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")
                        .withString("name", "老王")
                        .withInt("age", 23)
                        .navigation(context)
                }

            }

//            R.id.iev_address -> {
//
//                ARouter.getInstance()
//                    .build("/DisplayActivity/Display")
//                    .withString("displayTab", "MyAddressFragment")
//                    .withInt("age", 23)
//                    .navigation()
//
//            }

            R.id.iev_evaluate -> {
                (parentFragment as MainFragment).startBrotherFragment(EvaluateManageFragment())


            }

            R.id.iev_service -> {


                (parentFragment as MainFragment)
                    .startBrotherFragment(
                        BackViewFragment.newInstance(
                            SharedPreferencesUtils.getParam(
                                mActivity,
                                "helpIndexUrl",
                                "-1"
                            ) as String
                        )
                    )


            }


            R.id.ll_card -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MembershipCardFragment")
                    .withInt("age", 23)
                    .navigation()

            }

            else -> {
                startActivity(Intent(mActivity, SignActivity::class.java))


            }
        }
    }

    override fun initView() {

        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
            tv_name.text = SharedPreferencesUtils.getParam(context, "nickName", "") as String
            tv_mobile.visibility = View.VISIBLE
            tv_mobile.text = SharedPreferencesUtils.getParam(context, "mobile", "") as String

            ImageLoaderUtils.load(
                mActivity,
                profile_image,
                "",
                R.drawable.icon_login_touxiang,
                R.drawable.icon_login_touxiang, 0
            )

        } else {
            tv_name.text = "请点击登录"
            tv_mobile.visibility = View.GONE
            ImageLoaderUtils.load(
                mActivity,
                profile_image,
                "",
                R.drawable.icon_login_touxiang,
                R.drawable.icon_login_touxiang, 0
            )
        }

        val dis = RxBus.default.toFlowable(LoginBean::class.java).subscribe {
            LogUtils.i("Main4Fragment", it.mobile)
            if (it.loginSuccess) {
                tv_name.text = SharedPreferencesUtils.getParam(context, "nickName", "") as String
                tv_mobile.visibility = View.VISIBLE
                tv_mobile.text = SharedPreferencesUtils.getParam(context, "mobile", "") as String
                ImageLoaderUtils.load(
                    mActivity,
                    profile_image,
                    "",
                    R.drawable.icon_login_touxiang,
                    R.drawable.icon_login_touxiang, 0
                )

            } else {
                tv_name.text = "请点击登录"
                tv_mobile.visibility = View.GONE
                ImageLoaderUtils.load(
                    mActivity,
                    profile_image,
                    "",
                    R.drawable.icon_login_touxiang,
                    R.drawable.icon_login_touxiang, 0
                )
            }
        }

        compositeDisposable.add(dis)


        val list = arrayOf(
            iev_evaluate,

            iev_service,
            iev_about,
            iev_browse,
            iv_message,
            ll_Integral,
            iv_setting,
            tv_name,
            ll_card,
            ll_sign,
            iev_Collection,
            rl0

        )

        list.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun getLayoutRes(): Int = R.layout.main4fragment
}