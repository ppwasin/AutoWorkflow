package com.boot.dynamicfeature.sample.consumer

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.boot.dynamicfeature.adapter.ServiceLoaderWrapper
import com.boot.dynamicfeature.adapter.SplitInstallWrapper
import com.boot.dynamicfeature.contract.OnDemandFeature
import com.boot.dynamicfeature.contract.OnInstallFeature
import com.boot.dynamicfeature.provider.DynamicFeatureProvider


@Composable
fun DFMConsumerScreen() {
	val dynamicFeatureProvider = rememberDFMProvider()
	var onDemandFeature by remember { mutableStateOf<OnDemandFeature.EntryPoint?>(null) }
	var onInstallFeature by remember { mutableStateOf<OnInstallFeature.Entrypoint?>(null) }

	LaunchedEffect(Unit){
		onDemandFeature = dynamicFeatureProvider.getDynamicFeature(OnDemandFeature())
			.onFailure { println("Error: $it") }
			.getOrNull()

		onInstallFeature = dynamicFeatureProvider.getDynamicFeature(OnInstallFeature())
			.getOrNull()
	}

	AppMaterialTheme {
		Surface {
			Column {
				Text(text = "OnDemand")
				Text(text = "${onDemandFeature?.getSomething()}")

				Text(text = "OnInstall")
				Text(text = "${onInstallFeature?.getSomething()}")
			}
		}
	}
}

@Composable
fun rememberDFMProvider(): DynamicFeatureProvider {
	val context = LocalContext.current
	return remember {
		DynamicFeatureProvider(
			splitInstallWrapper = SplitInstallWrapper(context.applicationContext as Application),
			serviceLoaderWrapper = ServiceLoaderWrapper()
		)
	}
}
