package com.boot.playground.animation

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatablePlayground(ok: Boolean) {
	// Start out gray and animate to green/red based on `ok`
	val color = remember { Animatable(Color.Gray) }
	LaunchedEffect(ok) { color.animateTo(if (ok) Color.Green else Color.Red) }
	Box(
		Modifier
			.fillMaxSize()
			.background(color.value))
}

// sealed interface AnimatableType {
//    sealed interface Boolean: AnimatableType {
//        object AnimateTo: Boolean
//        object SnapTo: Boolean
//    }
//    object animateDecay: AnimatableType
// }

// @Composable
// fun AnimatablePlayground(ok: Boolean, type: AnimatableType.Boolean) {
//    // Start out gray and animate to green/red based on `ok`
//    val color = remember { Animatable(Color.Gray) }
//    LaunchedEffect(ok) {
//        val changeToColor = if (ok) Color.Green else Color.Red
//        when(type){
//            AnimateTo -> color.animateTo(changeToColor)
//            SnapTo -> color.snapTo(changeToColor)
//        }
//    }
//    Box(Modifier.fillMaxSize().background(color.value))
// }
