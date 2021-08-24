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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.boot.components.fake.FakeBackend
import com.boot.components.fake.FakePlaceHolderConfig
import com.boot.components.search.FakeSearchFetcher.FakeItem
import com.boot.theme.AppTheme
import kotlinx.coroutines.flow.Flow

object FakeSearchFetcher {
  private const val PER_PAGE = 10
  private const val FAKE_TOTAL_ITEMS = 100
  const val STARTING_PAGE_INDEX = 1

  // Network
  private val fakeBackend =
    FakeBackend(FAKE_TOTAL_ITEMS) { index ->
      val page = (index / PER_PAGE) + 1
      FakeItem(id = index, text = "index: $index on page $page")
    }
  data class FakeItem(val id: Int, val text: String)

  // Source
  class FakeSource(
    private val searchInput: String,
    private val fakeBackend: FakeBackend<FakeItem>
  ) : PagingSource<Int, FakeItem>() {

    override fun getRefreshKey(state: PagingState<Int, FakeItem>): Int? {
      return state.anchorPosition?.let { anchorPosition ->
        state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
          ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
      }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FakeItem> {
      return try {
        val page = params.key ?: STARTING_PAGE_INDEX
        println("params.loadSize: ${params.loadSize}")
        val response = fakeBackend.getFakeItems(page, PER_PAGE)
        val isLastPage = response.page == response.totalPages
        LoadResult.Page(
          data = response.items.map { it.copy(text = "$searchInput: ${it.text}") },
          prevKey = if (page == 1) null else page - 1,
          nextKey = if (isLastPage) null else response.page.plus(1),
          // before, after is provide for enable place holder
          itemsBefore = FakePlaceHolderConfig.getBefore(response),
          itemsAfter = FakePlaceHolderConfig.getAfter(PER_PAGE, response)
        )
      } catch (e: Exception) {
        LoadResult.Error(e)
      }
    }
  }

  val searchInput by mutableStateOf("")
  private val datasource = derivedStateOf { FakeSource(searchInput, fakeBackend) }

  val pager: Flow<PagingData<FakeItem>> =
    Pager(
        config =
          PagingConfig(
            pageSize = PER_PAGE,
            enablePlaceholders = FakePlaceHolderConfig.isEnable,
          ),
        pagingSourceFactory = {
          println("pagingSourceFactory")
          FakeSource(searchInput, fakeBackend)
        }
      )
      .flow

  fun search(keywords: String) {
    datasource.value.invalidate()
  }
}

@Composable
fun SearchScreenSlot(onSubmit: (String) -> Unit, onBack: () -> Unit) {
  val (keywords, setKeywords) = remember { mutableStateOf("") }
  val paging: LazyPagingItems<FakeItem> = FakeSearchFetcher.pager.collectAsLazyPagingItems()

  LazyColumn {
    item { SearchInputRow(keywords, setKeywords) }
    items(items = paging, key = FakeItem::id) { item ->
      Box(Modifier.height(128.dp).fillMaxWidth()) {
        when {
          item != null -> Text(item.text)
          else -> Text("Show Place holder")
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
      paging.loadState.append == LoadState.Loading -> {
        item {
          CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
          )
        }
      }
      paging.loadState.refresh is LoadState.Error ->
        item {
          val e = paging.loadState.refresh as LoadState.Error
          Text(
            text = "Error FirstPage: ${e.error.localizedMessage}",
            modifier = Modifier.clickable { paging.retry() }
          )
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

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
  AppTheme {
    SearchScreenSlot(
      onSubmit = {},
      onBack = {},
    )
  }
}
