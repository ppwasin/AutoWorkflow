package com.boot.autoworkflow.appyx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.spotlight.Spotlight
import com.bumble.appyx.navmodel.spotlight.current
import com.bumble.appyx.navmodel.spotlight.hasNext
import com.bumble.appyx.navmodel.spotlight.hasPrevious
import com.bumble.appyx.navmodel.spotlight.operation.activate
import com.bumble.appyx.navmodel.spotlight.operation.next
import com.bumble.appyx.navmodel.spotlight.operation.previous
import kotlinx.coroutines.flow.map

class RootNode(
	buildContext: BuildContext,
	private val spotlight: Spotlight<NavTarget> =
		Spotlight(
			items = listOf(NavTarget.Child1, NavTarget.Child2),
			initialActiveIndex = 0,
			savedStateMap = mapOf(),
		),
	private val backStack: BackStack<NavTarget> =
		BackStack(
			initialElement = NavTarget.Child1,
			savedStateMap = buildContext.savedStateMap,
		)
) :
	ParentNode<RootNode.NavTarget>(
		buildContext = buildContext,
		navModel = backStack,
	) {
	@Composable
	private fun BottomTabs(currentTab: State<RootNode.NavTarget?>) {
		NavigationBar {
			listOf(
				BottomNavItem(
					name = "Home",
					route = NavTarget.Child1,
					icon = Icons.Rounded.Home,
				),
				BottomNavItem(
					name = "Create",
					route = NavTarget.Child2,
					icon = Icons.Rounded.AddCircle,
				),
				BottomNavItem(
					name = "Settings",
					route = NavTarget.Child3,
					icon = Icons.Rounded.Settings,
				),
			)
				.forEach { tab ->
					NavigationBarItem(
						icon = { tab.icon },
						label = { Text(tab.toString()) },
						selected = currentTab.value == tab.route,
						onClick = { spotlight.activate(tab.route) },
					)
				}
		}
	}

	enum class NavTarget {
		Child1,
		Child2,
		Child3
	}

	override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
		when (navTarget) {
			NavTarget.Child1 ->
				Child1Node(buildContext) {
					//        backStack.push(NavTarget.Child2)
					spotlight.activate(1)
				}
			NavTarget.Child2 ->
				Child2Node(buildContext) {
					//        backStack.pop()
					spotlight.activate(0)
				}
			NavTarget.Child3 ->
				Child2Node(buildContext) {
					//        backStack.pop()
					spotlight.activate(0)
				}
		}

	@Composable
	override fun View(modifier: Modifier) {
		val hasPrevious = spotlight.hasPrevious().collectAsState(initial = false)
		val hasNext = spotlight.hasNext().collectAsState(initial = false)
		val currentTab = spotlight.current().collectAsState(initial = null)

		Scaffold(
			modifier = modifier.fillMaxSize(),
			floatingActionButtonPosition = FabPosition.Center,
			floatingActionButton = { PageButtons(hasPrevious.value, hasNext.value) },
			bottomBar = { BottomTabs(currentTab) },
		) { Children(modifier = Modifier.padding(it), navModel = spotlight) }
	}

	@Composable
	private fun PageButtons(hasPrevious: Boolean, hasNext: Boolean) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(24.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			FilledIconButton(
				onClick = { spotlight.previous() },
				modifier =
				if (hasPrevious) Modifier.shadow(4.dp, IconButtonDefaults.filledShape)
				else Modifier,
				enabled = hasPrevious,
			) { Icon(Icons.Filled.ArrowBack, contentDescription = "Previous") }
			FilledIconButton(
				onClick = { spotlight.next() },
				modifier =
				if (hasNext) Modifier.shadow(4.dp, IconButtonDefaults.filledShape)
				else Modifier,
				enabled = hasNext,
			) { Icon(Icons.Filled.ArrowForward, contentDescription = "Next") }
		}
	}
}

data class BottomNavItem(
	val name: String,
	val route: RootNode.NavTarget,
	val icon: ImageVector,
)

fun <T : Any> Spotlight<T>.current() =
	elements.map { value -> value.current?.key?.navTarget }

private fun Spotlight<*>.activate(item: RootNode.NavTarget) {
	activate(item.ordinal)
}
