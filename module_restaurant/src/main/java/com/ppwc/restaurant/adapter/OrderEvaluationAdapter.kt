package com.ppwc.restaurant.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup

import com.chad.library.adapter.base.BaseViewHolder
import com.ppwc.restaurant.R
import com.yjhh.common.BaseApplication
import com.yjhh.common.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.foodlvadapter2.view.*
import kotlinx.android.synthetic.main.orderevaluationadapter.view.*


class OrderEvaluationAdapter(var context: Context, var lists: List<String>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<OrderEvaluationAdapter.ViewHolder>() {

    public var maxValue = 9


    constructor(context: Context, lists: List<String>, maxValue: Int) : this(context, lists) {
        this.maxValue = maxValue
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = View.inflate(context, R.layout.orderevaluationadapter, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = when {
        lists.size >= maxValue -> maxValue
        else -> 1 + lists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, adapterPosition: Int) {


        if (holder.adapterPosition == lists.size) {
            ImageLoaderUtils.loadImgId(
                BaseApplication.context,
                holder.itemView.iv_image,
                R.drawable.ic_upload,
                R.drawable.ic_upload,
                R.drawable.ic_upload,
                0
            )

            holder.itemView.tv_delete.visibility = View.GONE

            holder.itemView.setOnClickListener { v ->
                mOnItemClickListener?.onRecycleViewItemClick(
                    v,
                    adapterPosition,
                    false
                )
            }
        } else {
            ImageLoaderUtils.load(
                BaseApplication.context,
                holder.itemView.iv_image,
                lists[adapterPosition],
                R.drawable.icon_place,
                R.drawable.icon_place,
                0
            )
            holder.itemView.tv_delete.visibility = View.VISIBLE




            holder.itemView.tv_delete.setOnClickListener { v ->
                mOnItemChildClickListener?.onItemChildClick(v, adapterPosition)
            }
            holder.itemView.setOnClickListener { v ->
                mOnItemClickListener?.onRecycleViewItemClick(
                    v,
                    adapterPosition,
                    true
                )
            }
        }


    }


    fun setOnItemClickListener(listener: OnRecycleViewItemClickListener?) {
        this.mOnItemClickListener = listener
    }


    fun setOnItemChildClickListener(listener: OnRecycleViewItemChildClickListener) {
        this.mOnItemChildClickListener = listener
    }

    private var mOnItemClickListener: OnRecycleViewItemClickListener? = null
    private var mOnItemChildClickListener: OnRecycleViewItemChildClickListener? = null

    class ViewHolder(itemView: View) : BaseViewHolder(itemView)


    interface OnRecycleViewItemChildClickListener {
        fun onItemChildClick(view: View, position: Int)
    }

    interface OnRecycleViewItemClickListener {

        fun onRecycleViewItemClick(view: View, position: Int, flag: Boolean)
    }
}