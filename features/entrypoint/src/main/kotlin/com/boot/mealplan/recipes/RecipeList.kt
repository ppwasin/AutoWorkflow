package com.boot.mealplan.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boot.components.SubTitle
import com.boot.components.Title
import kotlin.time.Duration
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
  var count by mutableStateOf(0)

  init {
    viewModelScope.launch {
      while (true) {
        ++count
        delay(Duration.run { 1.seconds })
      }
    }
  }
}

@Composable
fun RecipeList(
  viewModel: RecipeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
  searchBar: @Composable () -> Unit
) {
  val dummyItems = remember { (0..5).toList() }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.Start,
  ) {
    item { Title("Plan Your Meal: ${viewModel.count}") }
    item {
      SubTitle(
        text = "Take care of the planet and your wallet at the same time",
      )
    }
    item { searchBar() }

    items(dummyItems) { item ->
      Surface(shadowElevation = 6.dp) {
        Row(
          Modifier.padding(60.dp)
            .background(MaterialTheme.colorScheme.onPrimary),
        ) {
          Text(item.toString())
          Text(item.toString())
          Text(item.toString())
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun RecipesPreview() {
  RecipeList {}
}
