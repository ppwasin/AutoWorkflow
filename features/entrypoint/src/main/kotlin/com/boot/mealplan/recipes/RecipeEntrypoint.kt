package com.boot.mealplan.recipes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.components.fake.FakeFactory
import com.boot.components.search.SearchButton
import com.boot.components.search.SearchScreenSlot
import com.boot.entrypoint.ui.RecipeList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

enum class RecipeRoute {
  List,
  Search,
  Details;

  fun route() = this.name
}

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun RecipeEntrypoint(navController: NavHostController = rememberNavController()) {
  val coroutineScope = rememberCoroutineScope()
  NavHost(navController = navController, startDestination = RecipeRoute.List.route()) {
    composable(RecipeRoute.List.route()) {
      RecipeList(
        searchBar = { SearchButton { navController.navigate(RecipeRoute.Search.route()) } }
      )
    }
    composable(RecipeRoute.Search.route()) {
      SearchScreenSlot(viewModel = remember { FakeFactory.createViewModel(coroutineScope) })
    }
    composable(RecipeRoute.Details.route()) { RecipeDetails() }
  }
}
