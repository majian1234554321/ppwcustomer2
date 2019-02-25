package com.yjhh.common.bean

class ShareModel(
    var id: String? = null,
    var type: String? = null
)

data class ShareModel2(
    val id: Int,
    val imageUrl: String,
    val linkUrl: String,
    val resultType: Int,
    val type: Int
)
