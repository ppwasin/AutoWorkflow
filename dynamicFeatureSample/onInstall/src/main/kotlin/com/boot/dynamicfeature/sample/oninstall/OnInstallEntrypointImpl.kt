package com.boot.dynamicfeature.sample.oninstall

import com.boot.dynamicfeature.contract.OnInstallEntryPoint
import com.google.auto.service.AutoService

@AutoService(OnInstallEntryPoint::class)
class OnInstallEntrypointImpl: OnInstallEntryPoint {
	override fun getSomething(): String {
		return "Hello from OnstallFeature"
	}
}
