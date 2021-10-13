package com.boot.components.link

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BltLinkAppenderTest {
    lateinit var target: BltLinkAppender

    data class Input(val isUserLogin: Boolean, val priceType: String)
    private val secanarios = listOf(
        Input(isUserLogin = false, priceType = "etc") to "",
        Input(isUserLogin = true, priceType = "etc") to ""
    )


    @BeforeEach
    fun setup() {
        target = BltLinkAppender(linkHelper = LinkHelperImpl(), infoQueryProvider = InfoQueryProviderImpl(FakeMemberService()))

        val result = target.appendAvailableParams("https://www.ex.com")
    }

}