package com.boot.components.fake

import com.boot.components.fake.data.FakeBackend
import com.boot.components.fake.data.FakeSearchRepository
import com.boot.components.fake.model.FakeItem
import com.boot.components.search.viewmodel.QueryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

object FakeFactory {
  @ExperimentalCoroutinesApi
  @FlowPreview
  fun createViewModel(scope: CoroutineScope) = QueryViewModel(createRepository(), scope, "")
  fun createRepository() = FakeSearchRepository(createBackend())
  fun createBackend() =
    FakeBackend(FakePagingConstant.TOTAL_ITEMS) { index ->
      val page = (index / FakePagingConstant.PER_PAGE) + 1
      FakeItem(id = index, text = "index: $index on page $page")
    }
}
