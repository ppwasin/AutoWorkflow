package com.boot.dynamicfeature.contract


interface OnDemandEntryPoint {
	fun getSomething(): String
}
val onDemandFeature = DynamicFeatureEntry(
	feature = DynamicFeature("onDemend"),
	entryPoint = OnDemandEntryPoint::class.java
)
