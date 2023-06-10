package com.boot.dynamicfeature.model

data class ServiceLoaderEvent<T>(val target: Result<T>) : DynamicFeatureEvent
