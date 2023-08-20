package com.boot.dynamicfeature.sample.consumer

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.boot.dynamicfeature.contract.DynamicFeatureEntry
import com.boot.dynamicfeature.model.DynamicFeatureEvent
import com.boot.dynamicfeature.model.ServiceLoaderEvent
import com.boot.dynamicfeature.repository.DynamicFeatureRepository
import com.boot.dynamicfeature.repository.DynamicServiceRepository
import com.boot.dynamicfeature.repository.ServiceLoaderWrapper
import com.boot.dynamicfeature.repository.SplitInstallEventMapper
import com.boot.dynamicfeature.repository.SplitInstallWrapper
import com.boot.dynamicfeature.usecase.DynamicFeatureProvider
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach


@Composable
fun rememberDFMUsecase(): DynamicFeatureProvider {
	val context = LocalContext.current
	return remember {
		DynamicFeatureProvider(
			dynamicFeatureRepository = DynamicFeatureRepository(
				splitInstallWrapper = TODO(),
				splitInstallEventMapper = SplitInstallEventMapper(),
			),
			dynamicServiceRepository = DynamicServiceRepository(ServiceLoaderWrapper()),
		)
	}
}

@Composable
inline fun <reified T> rememberDynamicFeature(
	dynamicFeatureProvider: DynamicFeatureProvider,
	featureEntry: DynamicFeatureEntry<T>
): DynamicFeatureState<T> {
	var event by remember { mutableStateOf<DynamicFeatureEvent?>(null) }
	var target by remember { mutableStateOf<T?>(null) }

	LaunchedEffect(Unit) {
		target = dynamicFeatureProvider.getDynamicFeature(featureEntry.feature, featureEntry.entryPoint)
			.onEach { event = it }
			.filterIsInstance<ServiceLoaderEvent<T>>()
			.first()
			.target.getOrNull()
	}


	return DynamicFeatureState(
		event = event,
		target = target,
	)
}
