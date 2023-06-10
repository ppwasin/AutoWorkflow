package com.boot.dynamicfeature.sample.consumer

import com.boot.dynamicfeature.model.DynamicFeatureEvent

data class DynamicFeatureState<T>(
	val event: DynamicFeatureEvent?,
	val target: T?
)
