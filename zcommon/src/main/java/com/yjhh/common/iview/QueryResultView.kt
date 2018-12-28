package com.yjhh.common.iview

import com.yjhh.common.base.BaseView
import com.yjhh.common.bean.QueryResultBean

interface QueryResultView :BaseView{
    fun  queryResultValue (model: QueryResultBean) {

    }
}