package com.paipaiwei.personal.ui.fragment


import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.barlibrary.ImmersionBar
import com.gyf.barlibrary.ImmersionBar.setTitleBar
import com.paipaiwei.personal.CurrentApplication

import com.yjhh.common.utils.LogUtils
import com.yjhh.common.utils.RxBus
import com.yjhh.common.utils.SharedPreferencesUtils

import com.paipaiwei.personal.R
import com.paipaiwei.personal.bean.LoginBean
import com.paipaiwei.personal.bean.UserinfoBean
import com.paipaiwei.personal.present.SectionUserPresent

import com.paipaiwei.personal.ui.activity.SignActivity
import com.paipaiwei.personal.ui.activity.UserInfoActivity
import com.paipaiwei.personal.ui.activity.evaluate.EvaluateManageFragment
import com.paipaiwei.personal.ui.activity.onepay.OnePayFragment
import com.paipaiwei.personal.view.UserInfoView
import com.tencent.tauth.Tencent
import com.yjhh.common.BaseApplication
import com.yjhh.common.Constants.APP_ID_QQ
import com.yjhh.common.utils.ImageLoaderUtils

import kotlinx.android.synthetic.main.main4fragment.*


class Main4Fragment : BaseMainFragment(), View.OnClickListener, UserInfoView {
    override fun onSuccess(main1bean: UserinfoBean) {
        tv_name.text = main1bean.nickName
        tv_mobile.visibility = View.VISIBLE
        tv_mobile.text = main1bean.mobile
        if (!TextUtils.isEmpty(main1bean.avatarUrl)) {
            ImageLoaderUtils.loadCircle(
                mActivity,
                profile_image,
                main1bean.avatarUrl,
                R.drawable.icon_login_touxiang,
                5
            )
        }else{
            ImageLoaderUtils.load(
                mActivity,
                profile_image,
                "",
                R.drawable.icon_login_touxiang,
                R.drawable.icon_login_touxiang, 0
            )
        }


        ll_3.visibility = View.VISIBLE


        if (main1bean.account != null) {
            tv_Integral.text = main1bean.account.integral
            tv_sign.text = main1bean.account.sign
            tv_counp.text = main1bean.account.coupon
        }


    }

    override fun onFault(errorMsg: String?) {

    }


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.iev_about -> {


                (parentFragment as MainFragment).startBrotherFragment(AboutFragment())


            }


            R.id.iev_prop -> {


                (parentFragment as MainFragment).startBrotherFragment(OnePayFragment())

            }


            R.id.iev_browse -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "CollectionFragment")
                        .withString("value", "浏览")
                        .navigation(context)
                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")
                        .navigation(context)
                }


            }

            R.id.iv_message -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    (parentFragment as MainFragment).startBrotherFragment(MessageCenterFragment())
                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")
                        .navigation(context)
                }
            }

            R.id.iev_Collection -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    ARouter.getInstance()
                        .build("/DisplayActivity/Display")
                        .withString("displayTab", "CollectionFragment")
                        .withString("value", "收藏")
                        .navigation(context)



                    //(parentFragment as MainFragment).startBrotherFragment(CollectionFragment.newInstance("收藏"))


                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")

                        .navigation(context)
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
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")

                        .navigation(context)
                }

            }


            R.id.iv_setting -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "SettingFragment")
                    .navigation()
            }

            R.id.tv_name -> {

                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    startActivity(Intent(mActivity, UserInfoActivity::class.java))
                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")

                        .navigation(context)
                }


            }

            R.id.rl0 -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
                    startActivity(Intent(mActivity, UserInfoActivity::class.java))

                } else {
                    ARouter.getInstance()
                        .build("/LoginActivity/Login")

                        .navigation(context)
                }

            }

//            R.id.iev_address -> {
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


                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "BackViewFragment")
                    .withString(
                        "value", SharedPreferencesUtils.getParam(
                            mActivity,
                            "helpIndexUrl",
                            "-1"
                        ) as String
                    )
                    .navigation()


            }


            R.id.ll_card -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MembershipCardFragment")
                    .withInt("age", 23)
                    .navigation()

            }
            R.id.iev_Invitation ->{
                (parentFragment as MainFragment).startBrotherFragment(InvitationFragment())
            }

            else -> {
                startActivity(Intent(mActivity, SignActivity::class.java))


            }
        }
    }

    override fun initView() {


        ImmersionBar.setTitleBar(mActivity, toolbar)

        updateUI(!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String))

        val dis = RxBus.default.toFlowable(LoginBean::class.java).subscribe {
            LogUtils.i("Main4Fragment", it.mobile)
            updateUI(it.loginSuccess)
        }
        compositeDisposable.add(dis)


        val list = arrayOf(
            iev_evaluate,
            iev_service,
            iev_about,
            iev_browse,
            iev_prop,
            ll_Integral,
            iv_setting,
            tv_name,
            ll_card,
            ll_sign,
            iev_Collection,
            iev_Invitation,
            iv_message,
            rl0

        )

        list.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun getLayoutRes(): Int = R.layout.main4fragment

    fun updateUI(flag: Boolean) {
        if (flag) {
            clickLoadData()
        } else {
            ll_3.visibility = View.GONE
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

    override fun onSupportVisible() {
        super.onSupportVisible()
        val present2 = SectionUserPresent(context, this)
        present2?.getUserinfo()
    }


    fun clickLoadData() {
        val present2 = SectionUserPresent(context, this)
        present2?.getUserinfo()
    }


}