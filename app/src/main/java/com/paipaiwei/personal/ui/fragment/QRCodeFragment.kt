package com.paipaiwei.personal.ui.fragment


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

import android.view.*
import android.widget.ImageView

import android.widget.TextView


import com.youth.banner.Banner

import android.view.ViewGroup
import android.view.Gravity
import android.R.attr.gravity

import android.view.WindowManager
import android.util.DisplayMetrics
import android.view.Window.FEATURE_NO_TITLE
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.annotation.Nullable
import com.yjhh.common.utils.GlideLoader
import com.paipaiwei.personal.R


import android.util.Log
import com.uuzuche.lib_zxing.encoding.CodeCreator



@SuppressLint("ValidFragment")
class QRCodeFragment(var value: String) : androidx.fragment.app.DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.qrcodefragment, null);


        val tv_tips = view.findViewById<TextView>(R.id.tv_tips)

        val rl1 = view.findViewById<RelativeLayout>(R.id.rl1)
        //rl.setOnClickListener { dismiss() }

        val iv_qrcode = view.findViewById<ImageView>(R.id.iv_qrcode)


        val bitmap = CodeCreator.createQRCode(value, rl1.layoutParams.width,rl1.layoutParams.width,null);
        if (bitmap != null) {
            iv_qrcode.setImageBitmap(bitmap)

            Log.i("EncodingHandler", rl1.layoutParams.width.toString())
        }

        val iv_close = view.findViewById<ImageView>(R.id.iv_close)

        iv_close.setOnClickListener {
            dismiss()
        }

        builder.setView(view)
        val dialog = builder.create()
        return dialog
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // dialog.window!!.attributes.windowAnimations = R.style.dialogAnim
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val win = dialog?.window
        // 一定要设置Background，如果不设置，window属性设置无效
        win!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)

        val params = win.attributes
        params.gravity = Gravity.CENTER
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        win.attributes = params
    }


}