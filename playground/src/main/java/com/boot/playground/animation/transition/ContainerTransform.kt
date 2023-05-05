@file:OptIn(ExperimentalAnimationApi::class)

package com.boot.playground.animation.transition

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentWithReceiverOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boot.components.gesture.bounceClick
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.boot.designsystem.transition.SceneHost
import com.boot.designsystem.transition.SceneScope

@Composable
fun ContainerTransform() {
	var isCollpase by remember { mutableStateOf(true) }
	val A = remember {
		movableContentWithReceiverOf<SceneScope, Modifier> { modifier ->
			Box(
				modifier = Modifier
					.sharedElement()
					.then(modifier)
					.background(color = Color(0xfff3722c)),
			)
		}
	}
	val B = remember {
		movableContentWithReceiverOf<SceneScope, Modifier> { modifier ->
			Box(
				modifier = Modifier
					.sharedElement()
					.bounceClick { isCollpase = !isCollpase }
					.then(modifier)
					.background(color = Color(0xff90be6d)),
			)
		}
	}

	SceneHost(
		Modifier
			.fillMaxSize()
	) {
		if (isCollpase) {
			Column(
				Modifier
					.fillMaxSize()
					.background(Color.Gray)
					.padding(16.dp),
			) {
				A(
					Modifier
						.fillMaxWidth()
						.height(64.dp)
				)
				B(
					Modifier
						.fillMaxWidth()
						.height(64.dp),
				)
			}
		} else {
			Column(
				Modifier
					.fillMaxSize()
					.background(Color.White)
			) {
				B(
					Modifier
						.fillMaxWidth()
						.height(96.dp),
				)
				Spacer(modifier = Modifier.size(16.dp))
				var contentVisible by remember { mutableStateOf(false) }
				LaunchedEffect(Unit){
					contentVisible = true
				}
				AnimatedVisibility(
					visible  = contentVisible,
					enter = fadeIn(animationSpec = tween(delayMillis = 250)),
					exit = fadeOut(),
				) {
					Text(text = "Hello")
				}
			}

		}
	}
}


@Preview
@Composable
fun ContainerTransformPlayground() {
	AppMaterialTheme {
		ContainerTransform()
	}
}
