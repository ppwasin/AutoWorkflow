package com.boot.playground.animation.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

@Composable
fun AnimationContainer(content: @Composable (Boolean) -> Unit) {
	val (enabled, setIsEnabled) = remember { mutableStateOf(true) }
	Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
		Row(
			modifier = Modifier.align(Alignment.CenterHorizontally),
			verticalAlignment = Alignment.CenterVertically
		) {
			Button(
				modifier = Modifier.padding(12.dp),
				onClick = { setIsEnabled(!enabled) },
			) { Text("Click To Animate") }
			Text(
				modifier = Modifier.padding(start = 4.dp),
				text = "isEnable: $enabled",
			)
		}
		Row {
			content(enabled)
		}

	}
}
