package com.boot.dynamicfeature.contract

data class DynamicFeatureEntry<T>(
	val feature: DynamicFeature,
	val entryPoint: Class<T>
)
