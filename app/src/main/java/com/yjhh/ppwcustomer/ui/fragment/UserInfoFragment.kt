package com.yjhh.ppwcustomer.ui.fragment

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TimePicker
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.bumptech.glide.Glide
import com.yjhh.common.base.BaseFragment
import com.yjhh.loginmodule.ui.LoginActivity
import com.yjhh.ppwcustomer.R
import com.yjhh.ppwcustomer.bean.UserinfoBean
import com.yjhh.ppwcustomer.present.SectionUserPresent
import com.yjhh.ppwcustomer.view.UserInfoView
import kotlinx.android.synthetic.main.userinfofragment.*
import java.text.SimpleDateFormat
import java.util.*

class UserInfoFragment : BaseFragment(), View.OnClickListener, UserInfoView {
    override fun onSuccess(main1bean: UserinfoBean) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        iev_nickName.setTextContent(main1bean.nickName)
        Glide.with(mActivity).load(main1bean.avaterUrl).into(iv_image)

    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        if(){
//        ( context as Activity).startActivity(Intent(context,LoginActivity::class.java))}
    }


    override fun getLayoutRes(): Int = R.layout.userinfofragment

    override fun initView() {
        iev_nickName.setOnClickListener(this)
        iev_birthday.setOnClickListener(this)
        iev_address.setOnClickListener(this)
        initTimePicker()

        val present = SectionUserPresent(context, this)
        present.getUserinfo()


    }


    private fun getTime(date: Date): String {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.time)
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }


    var pvTime: TimePickerView? = null

    private fun initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = TimePickerBuilder(mActivity, OnTimeSelectListener { date, v ->
            Toast.makeText(mActivity, getTime(date), Toast.LENGTH_SHORT).show()
            Log.i("pvTime", "onTimeSelect")
            iev_birthday.setTextContent(getTime(date))
        })
            .setTimeSelectChangeListener { Log.i("pvTime", "onTimeSelectChanged") }
            .setType(booleanArrayOf(true, true, true, false, false, false))
            .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
            .build()

        val mDialog = pvTime?.getDialog()
        if (mDialog != null) {

            val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
            )

            params.leftMargin = 0
            params.rightMargin = 0
            pvTime?.dialogContainerLayout?.layoutParams = params

            val dialogWindow = mDialog.window
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM)//改成Bottom,底部显示
            }


        }
    }


    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        when (v?.id) {
            R.id.iev_nickName -> {

            }
            R.id.iev_birthday -> {
                pvTime?.show(v)
            }
            R.id.iev_address -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "MyAddressFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            else -> {
            }
        }
    }

}