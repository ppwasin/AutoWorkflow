package com.boot.playground

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.external.appsflyerwrapper.ui.AppsflyerScreen
import com.boot.playground.ads.AdsPlayground
import com.boot.playground.ads.AdsViewModel
import com.boot.playground.animation.AnimAsStatePlayground
import com.boot.playground.animation.AnimatablePlayground
import com.boot.playground.animation.control.DecayAnimationPlayground
import com.boot.playground.animation.control.InfiniteTransitionPlayground
import com.boot.playground.animation.control.TargetBasedAnimationPlayground
import com.boot.playground.animation.transition.lookahead.ContainerTransform
import com.boot.playground.animation.transition.lookahead.ContainerTransformList
import com.boot.playground.flow.FlowTestScreen
import com.boot.playground.permission.PermissionScreen
import com.boot.playground.uri.UriPlayground

@Composable
fun Playground() {
	val navController = rememberNavController()
	val routes = remember { PlaygroundRoute.values() }

	Surface {
		NavHost(navController = navController, startDestination = "initial") {
			composable("initial") {
				LazyColumn(
					horizontalAlignment = Alignment.Start,
					modifier = Modifier
						.fillMaxSize()
						.padding(16.dp),
				) {
					routes.forEach { route ->
						item {
							Button(
								modifier = Modifier.fillMaxWidth(),
								onClick = { navController.navigate(route.name) },
								shape = RoundedCornerShape(8.dp),
							) { Text(route.name) }
						}
					}
				}
			}
			routes.map { route -> composable(route.name) { route.Screen() } }
		}
	}
}

enum class PlaygroundRoute {
	AnimAsState,
	Animatable,
	TragetAnimation,
	InfiniteTransition,
	DecayAnimation,
	AdsId,
	Appsflyer,
	Permission,
	FlowTest,
	Uri,
	ContainerTransform,
	CTList
	;

	@Composable
	fun Screen() {
		val context = LocalContext.current
		return when (this) {
			AnimAsState -> AnimAsStatePlayground()
			Animatable -> AnimatablePlayground()
			TragetAnimation -> TargetBasedAnimationPlayground()
			InfiniteTransition -> InfiniteTransitionPlayground()
			DecayAnimation -> DecayAnimationPlayground()
			AdsId ->
				AdsPlayground(
					viewModel =
					viewModel {
						AdsViewModel(context.applicationContext as Application)
					},
				)
			Appsflyer -> AppsflyerScreen()
			Permission ->
				PermissionScreen {
					Column {
						Divider(modifier = Modifier.padding(vertical = 16.dp))
						Text("User allow permission")
					}
				}
			FlowTest -> FlowTestScreen()
			Uri -> UriPlayground()
			ContainerTransform -> ContainerTransform()
			CTList -> ContainerTransformList()
		}
	}
}
