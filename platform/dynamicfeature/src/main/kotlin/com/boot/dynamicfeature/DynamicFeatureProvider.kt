package com.boot.dynamicfeature

import com.boot.dynamicfeature.adapter.ServiceLoaderWrapper
import com.boot.dynamicfeature.adapter.SplitInstallWrapper
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

class DynamicFeatureProvider(
	private val splitInstallWrapper: SplitInstallWrapper,
	private val serviceLoaderWrapper: ServiceLoaderWrapper
) {
	suspend fun <T> getDynamicFeature(feature: DynamicFeatures<T>): Result<T> {
		return runCatching {
			if (!splitInstallWrapper.isFeatureInstall(feature.moduleName)) {
				installFeature(feature.moduleName)
			}

			getFeatureClass(feature.classPath)
		}
	}

	private suspend fun installFeature(moduleName: String) {
		splitInstallWrapper.installFeature(moduleName)
			.filter { it is SplitInstallState.Done && it.mainState == SplitInstallState.MainState.Install }
			.first()
	}

	private fun <T> getFeatureClass(classPath: Class<T>): T & Any {
		return serviceLoaderWrapper.loadClass(classPath) ?: throw Error("Class not found $classPath")
	}
}
