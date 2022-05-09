package com.boot.shopping

import com.boot.shopping.db.DriverFactory
import com.boot.shopping.db.ShoppingDatabase
import com.boot.shopping.di.ShoppingDi

suspend fun main() {
    val driver = DriverFactory().createDriver()
    ShoppingDi(
        httpClient = KtorClientFactory.createHttpClient(),
        database = ShoppingDatabase(driver)
    ).getShoppingItems()
}