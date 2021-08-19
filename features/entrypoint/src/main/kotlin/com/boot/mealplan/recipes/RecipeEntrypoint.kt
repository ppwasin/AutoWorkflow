package com.boot.mealplan.recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.components.search.SearchScreen
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
      RecipeList(onClickSearch = { navController.navigate(RecipeRoute.Search.route()) })
    }
    composable(RecipeRoute.Search.route()) {
      SearchScreen(onBack = { navController.popBackStack() }, onSubmit = { /* TODO() */})
    }
    composable(RecipeRoute.Details.route()) { RecipeDetails() }
  }
}
