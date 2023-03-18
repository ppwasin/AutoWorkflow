package com.boot.components.utils

import android.util.Log

fun <T : Any> T.logDebug(message: String) {
	Log.d(this::class.simpleName, message)
}

fun <T : Any> T.logVerbose(message: String) {
	Log.v(this::class.simpleName, message)
}

fun <T : Any> T.logInfo(message: String) {
	Log.i(this::class.simpleName, message)
}

fun <T : Any> T.logWarn(message: String) {
	Log.w(this::class.simpleName, message)
}

fun <T : Any> T.logError(message: String) {
	Log.e(this::class.simpleName, message)
}
