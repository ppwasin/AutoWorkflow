package com.boot.components.link.dep

interface MemberService {
    fun isLogin(): Boolean
    fun getMemberId(): Int?
}