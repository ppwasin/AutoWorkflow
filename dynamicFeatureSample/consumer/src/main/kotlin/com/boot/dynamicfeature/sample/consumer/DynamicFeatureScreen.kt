package com.boot.dynamicfeature.sample.consumer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.boot.dynamicfeature.contract.onDemandFeature
import com.boot.dynamicfeature.contract.onInstallFeature


@Composable
fun DFMConsumerScreen() {
	val dynamicFeatureProvider = rememberDFMUsecase()
	val onDemandFeatureState = rememberDynamicFeature(dynamicFeatureProvider, onDemandFeature)
	val onInstallFeatureState = rememberDynamicFeature(dynamicFeatureProvider, onInstallFeature)

	AppMaterialTheme {
		Surface {
			Column {
				Text(text = "OnDemand")
				with(onDemandFeatureState) {
					Text(text = "$event")
					Text(text = "Execute target: ${target?.getSomething()}")
				}

				Spacer(modifier = Modifier.height(32.dp))

				Text(text = "OnInstall")
				with(onInstallFeatureState) {
					Text(text = "$event")
					Text(text = "Execute target: ${target?.getSomething()}")
				}
			}
		}
	}
}

