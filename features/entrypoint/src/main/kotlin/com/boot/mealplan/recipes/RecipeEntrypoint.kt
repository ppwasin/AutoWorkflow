package com.boot.mealplan.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.components.search.SearchButton
import com.boot.components.search.SearchScreenSlot
import com.boot.fake.model.FakeItem

enum class RecipeRoute {
  Home,
  SearchResult,
  Details;

  fun route() = this.name
}

@Composable
fun RecipeEntrypoint(
  navController: NavHostController = rememberNavController()
) {
  NavHost(
    navController = navController,
    startDestination = RecipeRoute.Home.route(),
  ) {
    composable(RecipeRoute.Home.route()) {
      RecipeList(
        searchBar = {
          SearchButton {
            navController.navigate(RecipeRoute.SearchResult.route())
          }
        },
      )
    }
    composable(RecipeRoute.SearchResult.route()) {
      SearchScreenSlot(
        viewModel = RecipeInjector.rememberQueryViewModel(),
        itemKey = FakeItem::id,
        itemsContent = { item ->
          Box(Modifier.height(128.dp).fillMaxWidth()) {
            when {
              item != null -> Text(item.toString())
              else -> Text("Show Place holder")
            }
          }
        }
      )
    }
    composable(RecipeRoute.Details.route()) { RecipeDetails() }
  }
}
