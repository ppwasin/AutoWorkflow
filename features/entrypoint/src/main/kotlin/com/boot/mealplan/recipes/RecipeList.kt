package com.boot.mealplan.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boot.components.SubTitle
import com.boot.components.Title

@Composable
fun RecipeList(searchBar: @Composable () -> Unit) {
  val dummyItems = remember { (0..5).toList() }
  LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.Start
  ) {
    item { Title("Plan Your Meal") }
    item { SubTitle(text = "Take care of the planet and your wallet at the same time") }
    item { searchBar() }

    items(dummyItems) { item -> Text(item.toString()) }
  }
}

@Preview(showBackground = true)
@Composable
fun RecipesPreview() {
  RecipeList {}
}
