package com.boot.common

import com.boot.common.db.DriverFactory
import com.boot.common.di.ShoppingDi
import com.boot.shopping.db.ShoppingDatabase

suspend fun main() {
    val driver = DriverFactory().createDriver()
    ShoppingDi(
        httpClient = KtorClientFactory.createHttpClient(),
        database = ShoppingDatabase(driver)
    ).getShoppingItems()
}