package com.boot.dynamicfeature

sealed interface DynamicFeatures<T> {
	val moduleName: String
	val classPath: Class<T>
}
