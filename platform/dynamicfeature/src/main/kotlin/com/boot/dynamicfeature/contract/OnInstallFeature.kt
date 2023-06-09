package com.boot.dynamicfeature.contract

data class OnInstallFeature(
	override val moduleName: String = "onInstall",
): DynamicFeatures<OnInstallFeature.Entrypoint> {
	interface Entrypoint {
		fun getSomething(): String
	}
}
