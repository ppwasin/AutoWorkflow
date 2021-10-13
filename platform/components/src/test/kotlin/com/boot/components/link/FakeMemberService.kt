package com.boot.components.link

import com.boot.components.link.dep.MemberService

class FakeMemberService: MemberService {
    private var userId: Int? = null
    fun login(userId: Int){
        this.userId = userId
    }

    override fun isLogin(): Boolean {
        return userId != null
    }

    override fun getMemberId(): Int? {
        return userId
    }
}