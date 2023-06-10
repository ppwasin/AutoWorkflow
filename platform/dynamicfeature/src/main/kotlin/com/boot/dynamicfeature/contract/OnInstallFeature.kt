package com.boot.dynamicfeature.contract

interface OnInstallEntryPoint {
	fun getSomething(): String
}
val onInstallFeature = DynamicFeatureEntry(
	feature = DynamicFeature("onInstall"),
	entryPoint = OnInstallEntryPoint::class.java
)
