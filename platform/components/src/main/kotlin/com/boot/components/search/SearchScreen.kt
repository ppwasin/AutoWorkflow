package com.boot.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.boot.components.search.viewmodel.QueryViewModel

@Composable
fun <T : Any> SearchScreenSlot(
	viewModel: QueryViewModel<String, T>,
	itemsContent: @Composable (T?) -> Unit
) {
	val (query, setQuery) = remember { viewModel.query }
	val lazyPagingItems = viewModel.state.collectAsLazyPagingItems()
	Column {
		SearchInputRow(query, setQuery)
		LazyColumn {
			if (lazyPagingItems.loadState.refresh is LoadState.NotLoading) {
				if (lazyPagingItems.itemCount == 0) {
					item { Text("Empty content") }
				} else {
					items(count = lazyPagingItems.itemCount, key = lazyPagingItems.itemKey()) { index ->
						itemsContent(lazyPagingItems[index])
					}
				}
			}
			when {
				lazyPagingItems.loadState.refresh == LoadState.Loading ->
					item {
						Text(
							text = "Waiting for items to load from the backend",
							modifier =
							Modifier
								.fillParentMaxSize()
								.wrapContentSize(align = Alignment.Center),
						)
					}

				lazyPagingItems.loadState.refresh is LoadState.Error ->
					item {
						val e = lazyPagingItems.loadState.refresh as LoadState.Error
						Text(
							text = "Error FirstPage: ${e.error.localizedMessage}",
							modifier = Modifier.clickable { lazyPagingItems.retry() },
						)
					}

				lazyPagingItems.loadState.append == LoadState.Loading ->
					item {
						CircularProgressIndicator(
							modifier =
							Modifier
								.fillMaxWidth()
								.wrapContentWidth(Alignment.CenterHorizontally),
						)
					}

				lazyPagingItems.loadState.append is LoadState.Error ->
					item {
						val e = lazyPagingItems.loadState.append as LoadState.Error
						Text(
							text = "Error FirstPage: ${e.error.localizedMessage}",
							modifier = Modifier.clickable { lazyPagingItems.retry() },
						)
					}

				lazyPagingItems.loadState.append.endOfPaginationReached ->
					item { Text("end of pagination") }
			}
		}
	}
}
