package com.paipaiwei.personal.ui.fragment

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
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.jakewharton.rxbinding2.view.RxView.visibility
import com.paipaiwei.personal.R
import com.ppwc.restaurant.views.ProofreadingDialogFragment


@SuppressLint("ValidFragment")
class QiangPaiDialogFragment(var status: String?, var tips: String?, var remark: String?) : DialogFragment() {

    fun setOnDialogClick(listener: OnDialogClickListener) {
        this.listener = listener
    }

    var listener: OnDialogClickListener? = null

    interface OnDialogClickListener {
        fun onDialogClick()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val view = View.inflate(activity, R.layout.qiangpaidialogfragment, null)
        view.findViewById<TextView>(R.id.tv_tips).text = tips
        val mb_pay = view.findViewById<TextView>(R.id.mb_pay)

        val tv_remark = view.findViewById<TextView>(R.id.tv_remark)
        tv_remark.text = remark
        if ("抢拍成功" == status) {
            tv_remark.visibility = View.VISIBLE
            mb_pay.setBackgroundColor(Color.parseColor("#E6460A"))
            mb_pay.text = "立即付款"
        } else {
            tv_remark.visibility = View.INVISIBLE
            mb_pay.setBackgroundColor(Color.parseColor("#EE9E15"))
            mb_pay.text = "查看其它抢拍"


        }
        view.findViewById<TextView>(R.id.tv_status).text = status
        view.findViewById<ImageView>(R.id.iv_status)
            .setBackgroundResource(if ("抢拍成功" == status) R.drawable.xiao022x else R.drawable.ku032x)



        mb_pay.setOnClickListener {
            if (listener != null) {
                listener?.onDialogClick()

            }
        }






        builder.setView(view)
        val dialog = builder.create()
        return dialog

    }


    override fun onStart() {
        super.onStart()
        val win = dialog.window
        // 一定要设置Background，如果不设置，window属性设置无效
        win!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent);
        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)

        val params = win.attributes
        params.gravity = Gravity.CENTER
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕

        params.width = (dm.widthPixels * 0.80).toInt()
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT


        Log.i("ProofreadingDialog", params.width.toString())

        win.attributes = params
    }


}