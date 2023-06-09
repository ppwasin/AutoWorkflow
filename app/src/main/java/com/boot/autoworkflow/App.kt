package com.boot.autoworkflow

import android.app.Application
import android.content.Context
import com.boot.payment.BillingSdk
import com.google.android.play.core.splitcompat.SplitCompat

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		val test = BillingSdk(this)
		ShowkaseInit.addShortcut(this)
	}

	override fun attachBaseContext(base: Context) {
		super.attachBaseContext(base)
		SplitCompat.install(this)
	}
}
