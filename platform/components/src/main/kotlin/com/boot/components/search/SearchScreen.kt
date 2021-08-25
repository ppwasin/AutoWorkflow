package com.boot.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.boot.components.fake.model.FakeItem
import com.boot.components.search.viewmodel.QueryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun SearchScreenSlot(viewModel: QueryViewModel<String, FakeItem>) {
  val (query, setQuery) = remember { viewModel.query }
  val paging = viewModel.state.collectAsLazyPagingItems()

  LazyColumn {
    item { SearchInputRow(query, setQuery) }
    if (paging.loadState.refresh != LoadState.Loading) {
      items(items = paging, key = FakeItem::id) { item ->
        Box(Modifier.height(128.dp).fillMaxWidth()) {
          when {
            item != null -> Text(item.text)
            else -> Text("Show Place holder")
          }
        }
      }
    }
    when {
      paging.loadState.refresh == LoadState.Loading ->
        item {
          Text(
            text = "Waiting for items to load from the backend",
            modifier = Modifier.fillParentMaxSize().wrapContentSize(align = Alignment.Center)
          )
        }
      paging.loadState.refresh is LoadState.Error ->
        item {
          val e = paging.loadState.refresh as LoadState.Error
          Text(
            text = "Error FirstPage: ${e.error.localizedMessage}",
            modifier = Modifier.clickable { paging.retry() }
          )
        }
      paging.loadState.append == LoadState.Loading -> {
        item {
          CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
          )
        }
      }
      paging.loadState.append is LoadState.Error ->
        item {
          val e = paging.loadState.append as LoadState.Error
          Text(
            text = "Error FirstPage: ${e.error.localizedMessage}",
            modifier = Modifier.clickable { paging.retry() }
          )
        }
      paging.loadState.append.endOfPaginationReached -> item { Text("end of pagination") }
    }
  }
}
