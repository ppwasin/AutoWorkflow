package com.boot.dynamicfeature.repository

import com.boot.dynamicfeature.model.ServiceLoaderEvent

class DynamicServiceRepository(
	private val serviceLoaderWrapper: ServiceLoaderWrapper
) {
	fun <T> loadClassResult(clazz: Class<T>): ServiceLoaderEvent<T> {
		return runCatching {
			serviceLoaderWrapper.loadClass(clazz) ?: error("Not found implementation of $clazz")
		}.let { ServiceLoaderEvent(it) }
	}
}
