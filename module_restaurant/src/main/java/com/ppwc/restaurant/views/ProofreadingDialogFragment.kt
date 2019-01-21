package com.ppwc.restaurant.views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.ppwc.restaurant.R

import me.jessyan.autosize.internal.CustomAdapt

@SuppressLint("ValidFragment")
class ProofreadingDialogFragment(var tv1: String, var tv2: String, var tv3: String, var tv4: String) : DialogFragment(),
    CustomAdapt {

    override fun isBaseOnWidth(): Boolean {
        return true
    }

    override fun getSizeInDp(): Float {
        return 375f
    }

    var listener : OnDialogClickListener? = null

    fun setOnDialogClick(listener : OnDialogClickListener){
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val view = View.inflate(activity, R.layout.proofreadingdialogfragment, null)


        val textView1 = view.findViewById<TextView>(R.id.tv_1)
        val textView2 = view.findViewById<TextView>(R.id.tv_2)
        val textView3 = view.findViewById<TextView>(R.id.tv_3)
        val textView4 = view.findViewById<TextView>(R.id.tv_4)

        val rl1 = view.findViewById<RelativeLayout>(R.id.rl1)
        val rl2 = view.findViewById<RelativeLayout>(R.id.rl2)
        val rl3 = view.findViewById<RelativeLayout>(R.id.rl3)
        val rl4 = view.findViewById<RelativeLayout>(R.id.rl4)

        val mb_pay = view.findViewById<MaterialButton>(R.id.mb_pay)

        mb_pay.setOnClickListener {
            listener?.onDialogClick()
        }



        if ("0" == tv1) {
            rl1.visibility = View.GONE
        } else {
            textView1.text = tv1
            rl1.visibility = View.VISIBLE
        }

        if ("0" == tv2) {
            rl2.visibility = View.GONE
        } else {
            textView2.text = tv2
            rl2.visibility = View.VISIBLE
        }


        if ("0" == tv3) {
            rl3.visibility = View.GONE
        } else {
            textView3.text = tv3
            rl3.visibility = View.VISIBLE
        }

        if ("0" == tv4) {
            rl4.visibility = View.GONE
        } else {
            textView4.text = tv4
            rl4.visibility = View.VISIBLE
        }












        builder.setView(view)
        val dialog = builder.create()
        return dialog

    }


    interface OnDialogClickListener {
        fun onDialogClick()
    }


    override fun onStart() {
        super.onStart()
        val win = dialog.window
        // 一定要设置Background，如果不设置，window属性设置无效
        win!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)

        val params = win.attributes
        params.gravity = Gravity.CENTER
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕

        params.width = (dm.widthPixels * 0.90).toInt()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT


        Log.i("ProofreadingDialog", params.width.toString())

        win.attributes = params
    }


}