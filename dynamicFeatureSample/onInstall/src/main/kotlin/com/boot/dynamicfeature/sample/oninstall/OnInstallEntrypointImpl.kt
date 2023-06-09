package com.boot.dynamicfeature.sample.oninstall

import com.boot.dynamicfeature.contract.OnInstallFeature
import com.google.auto.service.AutoService

@AutoService(OnInstallFeature.Entrypoint::class)
class OnInstallEntrypointImpl: OnInstallFeature.Entrypoint {
	override fun getSomething(): String {
		return "Hello from OnstallFeature"
	}
}
