package com.boot.components.fake.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.boot.components.fake.FakePagingConstant
import com.boot.components.fake.FakePlaceHolderConfig
import com.boot.components.fake.model.FakeItem
import com.boot.components.search.data.QueryRepository

class FakeSearchRepository(private val fakeBackend: FakeBackend<FakeItem>) :
  QueryRepository<String, FakeItem> {
  override fun buildPager(queryInput: String): Pager<Int, FakeItem> {
    return Pager(
      config =
        PagingConfig(
          pageSize = FakePagingConstant.PER_PAGE,
          enablePlaceholders = FakePlaceHolderConfig.isEnable,
        ),
      pagingSourceFactory = { FakeSource(queryInput, fakeBackend) },
    )
  }
}
