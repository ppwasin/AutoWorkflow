package com.boot.playground.test

import android.util.Log

object TestLogger {
	fun log(message: String) {
		Log.d(
			"TestLog",
			message,
		)
	}
}
