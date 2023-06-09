package com.boot.dynamicfeature.contract


data class OnDemandFeature(
	override val moduleName: String = "onDemend",
) : DynamicFeatures<OnDemandFeature.EntryPoint> {
	interface EntryPoint {
		fun getSomething(): String
	}
}
