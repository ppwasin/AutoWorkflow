package com.boot.mealplan.recipes

import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.components.search.SearchButton
import com.boot.components.search.SearchScreenSlot
import com.boot.entrypoint.ui.RecipeList
import com.boot.mealplan.recipes.RecipeSearchAction.Content
import com.boot.mealplan.recipes.RecipeSearchAction.Error
import com.boot.mealplan.recipes.RecipeSearchAction.Searching

enum class RecipeRoute {
  List,
  Search,
  Details;

  fun route() = this.name
}

@Composable
fun RecipeEntrypoint(navController: NavHostController = rememberNavController()) {
  val (searchText, setSearchText) = remember { mutableStateOf("") }
  val (searchAction, setSearchAction) = remember { mutableStateOf<RecipeSearchAction?>(null) }
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
          when (searchAction) {
            is Content -> items(searchAction.content) { Text(it.toString()) }
            is Error -> item { Text("Error please try again (${searchAction.errorMsg})") }
            is Searching -> item { Text("Searching for ${searchAction.keyword}") }
          }
        }
      )
    }
    composable(RecipeRoute.Details.route()) { RecipeDetails() }
  }
}
