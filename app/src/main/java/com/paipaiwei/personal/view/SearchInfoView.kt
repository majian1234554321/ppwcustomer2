package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.SearchInfoBean
import com.yjhh.common.base.BaseView

interface SearchInfoView:BaseView {
    fun SearchInfoValue(modle : SearchInfoBean)
}