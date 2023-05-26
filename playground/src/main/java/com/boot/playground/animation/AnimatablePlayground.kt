package com.boot.playground.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.boot.playground.animation.base.AnimationContainer

@Composable
@Preview
fun AnimatablePlayground() {
	AppMaterialTheme {
		AnimationContainer {
			// Start out gray and animate to green/red based on `ok`
			val color = remember { Animatable(Color.Gray) }
			LaunchedEffect(it) {
				color.animateTo(
					targetValue = if (it) Color.Green else Color.Red,
					animationSpec = tween(delayMillis = 300, durationMillis = 2000),
				)
			}
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(color.value),
			)
		}
	}
}
