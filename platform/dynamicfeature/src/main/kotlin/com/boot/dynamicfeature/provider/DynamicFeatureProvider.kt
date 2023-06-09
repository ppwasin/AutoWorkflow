package com.boot.dynamicfeature.provider

import com.boot.dynamicfeature.adapter.ServiceLoaderWrapper
import com.boot.dynamicfeature.adapter.SplitInstallWrapper
import com.boot.dynamicfeature.contract.DynamicFeatures
import com.boot.dynamicfeature.logger.DynamicFeatureLogger
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach

class DynamicFeatureProvider(
	val splitInstallWrapper: SplitInstallWrapper,
	private val serviceLoaderWrapper: ServiceLoaderWrapper
) {
	suspend inline fun <reified T> getDynamicFeature(feature: DynamicFeatures<T>): Result<T> = runCatching {
		if (!splitInstallWrapper.isFeatureInstall(feature.moduleName)) {
			DynamicFeatureLogger.log("PlayCore: Attempt to install $feature")
			installFeature(feature.moduleName)
		}

		DynamicFeatureLogger.log("PlayCore: getFeatureClass $feature")
		getFeatureClass(T::class.java)
	}

	suspend fun installFeature(moduleName: String) {
		splitInstallWrapper.installFeature(moduleName)
			.onEach { DynamicFeatureLogger.log("Install state = $it") }
			.filter { it is SplitInstallState.Done && it.mainState == SplitInstallState.MainState.Install }
			.first()
	}

	fun <T> getFeatureClass(classPath: Class<T>): T & Any {
		return serviceLoaderWrapper.loadClass(classPath) ?: throw Error("Class not found $classPath")
	}
}
