package com.boot.fake.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.boot.components.fake.FakePagingConstant
import com.boot.components.fake.FakePlaceHolderConfig
import com.boot.fake.model.FakeItem
import kotlinx.coroutines.launch

class FakeSource(private val searchInput: String, private val fakeBackend: FakeBackend<FakeItem>) :
  PagingSource<Int, FakeItem>() {

  override fun getRefreshKey(state: PagingState<Int, FakeItem>): Int? {
    println("getRefreshKey: $state")
    return null
    //		return state.anchorPosition?.let { anchorPosition ->
    //			state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
    //				?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    //		}
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FakeItem> {
    return try {
      val page = params.key ?: FakePagingConstant.START_PAGE_INDEX
      println("load with query: $searchInput at page $page")
      val response = fakeBackend.getFakeItems(page, FakePagingConstant.PER_PAGE)
      val isLastPage = response.page == response.totalPages
      Page(
        data = response.items.map { it.copy(text = "$searchInput: ${it.text}") },
        prevKey = if (page == 1) null else page - 1,
        nextKey = if (isLastPage) null else response.page.plus(1),
        // before, after is provide for enable place holder
        itemsBefore = FakePlaceHolderConfig.getBefore(response),
        itemsAfter = FakePlaceHolderConfig.getAfter(FakePagingConstant.PER_PAGE, response)
      )
    } catch (e: Exception) {
      Error(e)
    }
  }
}

class BasicVieModel: ViewModel(){
  fun test(){
    viewModelScope.launch {

    }
  }
}