package com.paipaiwei.personal.ui.fragment


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

import android.view.*


import android.view.ViewGroup
import android.view.Gravity
import android.R.attr.gravity
import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator

import android.view.WindowManager
import android.util.DisplayMetrics
import android.view.Window.FEATURE_NO_TITLE
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Nullable
import com.yjhh.common.utils.GlideLoader
import com.paipaiwei.personal.R
import com.paipaiwei.personal.common.extend.Visitable
import kotlinx.android.synthetic.main.signfragment.*


@SuppressLint("ValidFragment")
class SignFragment(var value: String) : androidx.fragment.app.DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.signfragment, null);

        val rl_title = view.findViewById<RelativeLayout>(R.id.rl_title)
        val iv_close = view.findViewById<ImageView>(R.id.iv_close)

        val tv_text = view.findViewById<TextView>(R.id.tv_text)

        val rl_fish = view.findViewById<RelativeLayout>(R.id.rl_fish)

        val curTranslationY = rl_fish.getTranslationY();
        val translationY = ObjectAnimator.ofFloat(
            rl_fish, "translationY",
            curTranslationY, curTranslationY + 500f
        );

        val scaleY = ObjectAnimator.ofFloat(rl_fish, "scaleY", 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 0f)
        val scaleX = ObjectAnimator.ofFloat(rl_fish, "scaleX", 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 0f)

        val animSet = AnimatorSet()
        animSet.play(scaleY).with(scaleX)
        animSet.duration = 5000
        animSet.start()

        animSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                rl_title.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })






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