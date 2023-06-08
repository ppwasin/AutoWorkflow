package com.boot.autoworkflow

import android.app.Application
import com.boot.payment.BillingSdk

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		val test = BillingSdk(this)
		ShowkaseInit.addShortcut(this)
	}
}
