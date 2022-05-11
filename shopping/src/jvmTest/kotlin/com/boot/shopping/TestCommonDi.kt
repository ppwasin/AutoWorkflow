package com.boot.shopping

import com.boot.shopping.db.DriverFactory
import com.boot.shopping.db.ShoppingDatabase
import com.boot.shopping.di.ShoppingDi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test


class JvmTest {
    @Test
    fun test() = runTest {
        val driver = DriverFactory().createDriver()
        ShoppingDi(
            httpClient = KtorClientFactory.createHttpClient(),
            database = ShoppingDatabase(driver)
        ).getShoppingItems()
    }
}