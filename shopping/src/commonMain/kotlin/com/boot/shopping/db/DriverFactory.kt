package com.boot.shopping.db

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
	fun createDriver(): SqlDriver
}
