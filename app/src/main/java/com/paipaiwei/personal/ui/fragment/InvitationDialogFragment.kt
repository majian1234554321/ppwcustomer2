package com.paipaiwei.personal.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.ImageView


import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton

import com.paipaiwei.personal.R
import com.yjhh.common.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.invitationfragment.*
import org.w3c.dom.Text


@SuppressLint("ValidFragment")
class InvitationDialogFragment(var activity: Activity, var tips: String?,var imageUrl:String?) : DialogFragment() {

    interface OnDialogClickListener {
        fun onDialogClick()
    }

    var listener: OnDialogClickListener? = null

    public fun setOnClickListener(listener: OnDialogClickListener) {
        this.listener = listener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.invitationdialogfragment, null)

        val mb = view.findViewById<MaterialButton>(R.id.mb)
        val tv_tips = view.findViewById<TextView>(R.id.tv_tips)
        val iv_image = view.findViewById<ImageView>(R.id.iv_image)

        tv_tips.text = tips



        ImageLoaderUtils.loadCircle(
            activity,
            iv_image,
            imageUrl,
            R.drawable.icon_place_pai,
            R.drawable.icon_place_pai
        )




        mb.setOnClickListener {
            listener?.onDialogClick()
        }


        builder.setView(view)
        val dialog = builder.create()
        return dialog
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // dialog.window!!.attributes.windowAnimations = R.style.dialogAnim
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val win = dialog.window
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
