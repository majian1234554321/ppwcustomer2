package com.paipaiwei.personal.view

import com.paipaiwei.personal.bean.MembershipCardBean
import com.yjhh.common.base.BaseView

interface MembershipCardView :BaseView{
    fun onMembershipCardValue (model : MembershipCardBean)
}