package com.boot.dynamicfeature.repository

import java.util.ServiceLoader

class ServiceLoaderWrapper {

	fun <T> loadClass(classPath: Class<T>): T? {
		val serviceIterator = ServiceLoader.load(
			classPath,
			classPath.classLoader,
		).iterator()

		return if (serviceIterator.hasNext()) {
			serviceIterator.next()
		} else {
			null
		}
	}
}
