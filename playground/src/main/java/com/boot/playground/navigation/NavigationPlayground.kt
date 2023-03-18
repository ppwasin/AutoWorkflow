package com.boot.playground.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavigationPlaygroundScreen() {
	ExpandableFabBasic()
}

class PrelaunchRoot() {
	sealed interface NavTarget {
		object China : NavTarget
		object Skorea : NavTarget
		object Login : NavTarget
		object Cookie : NavTarget
	}

	@Composable
	fun Render() {
	}
}

@Preview
@Composable
fun NavigationPlaygroundPreview() {
	NavigationPlaygroundScreen()
}
