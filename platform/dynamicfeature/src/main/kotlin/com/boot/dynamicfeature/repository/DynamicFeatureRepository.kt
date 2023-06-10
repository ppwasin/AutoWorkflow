package com.boot.dynamicfeature.repository

import com.boot.dynamicfeature.contract.DynamicFeature
import com.boot.dynamicfeature.model.DynamicFeatureEvent
import com.boot.dynamicfeature.model.SplitInstallEvent
import com.boot.dynamicfeature.model.TerminatedEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile

class DynamicFeatureRepository(
	private val splitInstallWrapper: SplitInstallWrapper,
	private val splitInstallEventMapper: SplitInstallEventMapper,
) {
	fun installFeature(feature: DynamicFeature): Flow<SplitInstallEvent> =
		if (splitInstallWrapper.isFeatureInstall(feature))
			flowOf(SplitInstallEvent.Success)
		else splitInstallWrapper.installFeature(feature)
			.map(splitInstallEventMapper::mapSplitStateToEvent)

}
