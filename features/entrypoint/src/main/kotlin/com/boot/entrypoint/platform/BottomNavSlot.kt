package com.boot.entrypoint.platform

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun <T> BtmSlot(
  screenItems: Set<T>,
  infoGetter: BottomNavInfoGetter<T>,
  navigateTo: @Composable (T) -> Unit
) {
  val navController = rememberNavController()
  Scaffold(
    bottomBar = {
      NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screenItems.forEach { item ->
          NavigationBarItem(
            icon = { Icon(infoGetter.getIcon(item), contentDescription = null) },
            label = { Text(infoGetter.getLabel(item)) },
            selected =
              currentDestination?.hierarchy?.any { it.route == infoGetter.getRoute(item) } == true,
            onClick = {
              navController.navigate(infoGetter.getRoute(item)) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
              }
            }
          )
        }
      }
    }
  ) { innerPadding ->
    NavHost(
      navController,
      startDestination = infoGetter.getRoute(screenItems.first()),
      Modifier.padding(innerPadding)
    ) { screenItems.forEach { item -> composable(infoGetter.getRoute(item)) { navigateTo(item) } } }
  }
}
