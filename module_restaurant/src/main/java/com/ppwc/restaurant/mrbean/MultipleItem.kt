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
    var spanSize: Int = 0

    constructor(itemType: Int, spanSize: Int, content: String) {
        this.itemType = itemType
        this.spanSize = spanSize
        this.content = content
    }

    constructor(itemType: Int, spanSize: Int) {
        this.itemType = itemType
        this.spanSize = spanSize
    }

    var content: String? = null

    override fun getItemType(): Int {
        return itemType
    }

    companion object {
        val A = 1
        val B = 2
        val C = 3

    }
}
