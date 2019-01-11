package com.ppwc.restaurant.mrbean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * 文 件 名: MultipleItem
 * 创 建 人: Allen
 * 创建日期: 2017/6/13 14:20
 * 修改时间：
 * 修改备注：
 */
class MultipleItem : MultiItemEntity {
    private var itemType: Int = 0

    var flag: Boolean? = null

    constructor(itemType: Int) {
        this.itemType = itemType
    }


    var listone: List<RestaurantHomeBean.OneMoneyBean>? = null

    constructor(itemType: Int, flag: Boolean, listone: List<RestaurantHomeBean.OneMoneyBean>) {
        this.itemType = itemType
        this.flag = flag
        this.listone = listone
    }


    public var type: String? = null

    var listProducts: List<RestaurantHomeBean.ProductsBean>? = null

    constructor(itemType: Int, listProducts: List<RestaurantHomeBean.ProductsBean>, type: String) {
        this.itemType = itemType
        this.listProducts = listProducts
        this.type = type
    }


    var listUserComment: List<RestaurantHomeBean.UserCommentBean>? = null
    var size: Int = 0

    var commentLabel: List<RestaurantHomeBean.CommentLabel>? = null

    constructor(
        itemType: Int,
        listUserComment: List<RestaurantHomeBean.UserCommentBean>,
        size: Int,
        commentLabel: List<RestaurantHomeBean.CommentLabel>
    ) {
        this.itemType = itemType
        this.listUserComment = listUserComment
        this.size = size
        this.commentLabel = commentLabel;
    }


    var content: String? = null

    override fun getItemType(): Int {
        return itemType
    }

    companion object {
        val A = 1
        val B = 2
        val C = 3
        val D = 4

    }
}
