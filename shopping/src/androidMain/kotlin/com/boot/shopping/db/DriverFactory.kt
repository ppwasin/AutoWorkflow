package com.boot.shopping.db

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val application: Application) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ShoppingDatabase.Schema, application, "items.db")
    }
}