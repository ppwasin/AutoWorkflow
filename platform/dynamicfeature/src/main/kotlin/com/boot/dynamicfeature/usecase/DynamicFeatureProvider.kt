package com.boot.dynamicfeature.usecase

import com.boot.dynamicfeature.contract.DynamicFeature
import com.boot.dynamicfeature.logger.DynamicFeatureLogger
import com.boot.dynamicfeature.model.DynamicFeatureEvent
import com.boot.dynamicfeature.model.SplitInstallEvent
import com.boot.dynamicfeature.model.TerminatedEvent
import com.boot.dynamicfeature.repository.DynamicFeatureRepository
import com.boot.dynamicfeature.repository.DynamicServiceRepository
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class DynamicFeatureProvider(
	private val dynamicFeatureRepository: DynamicFeatureRepository,
	private val dynamicServiceRepository: DynamicServiceRepository
) {

	fun <T> getDynamicFeature(feature: DynamicFeature, clazz: Class<T>): Flow<DynamicFeatureEvent> = flow {
		val terminatedEvent = dynamicFeatureRepository.installFeature(feature)
			.onEach(::emit)
			.filterIsInstance<TerminatedEvent>()
			.first()

		require(terminatedEvent is SplitInstallEvent.Success) { "Failed to install feature $feature" }
		emit(dynamicServiceRepository.loadClassResult(clazz))

		awaitCancellation()
	}
		.catch { DynamicFeatureLogger.log("$feature: error: $it") }
		.onEach { DynamicFeatureLogger.log("$feature: event = $it") }
}
