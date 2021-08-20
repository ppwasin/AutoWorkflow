package com.boot.mealplan.recipes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.components.search.SearchButton
import com.boot.components.search.SearchItemSlot
import com.boot.components.search.SearchScreenSlot
import com.boot.entrypoint.ui.RecipeList

enum class RecipeRoute {
  List,
  Search,
  Details;

  fun route() = this.name
}

@Composable
fun RecipeEntrypoint(navController: NavHostController = rememberNavController()) {
  val (searchText, setSearchText) = remember { mutableStateOf("") }
  NavHost(navController = navController, startDestination = RecipeRoute.List.route()) {
    composable(RecipeRoute.List.route()) {
      RecipeList(
        searchBar = { SearchButton { navController.navigate(RecipeRoute.Search.route()) } }
      )
    }
    composable(RecipeRoute.Search.route()) {
      SearchScreenSlot(
        onBack = { navController.popBackStack() },
        onSubmit = { /* TODO() */},
        content = {
          item {
            SearchItemSlot(
              startIcon = Icons.Default.AccessAlarm,
              text = "Sample",
              endIcon = Icons.Default.ZoomIn
            )
          }
        }
      )
    }
    composable(RecipeRoute.Details.route()) { RecipeDetails() }
  }
}
