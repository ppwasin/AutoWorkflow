package com.boot.fake.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.boot.components.fake.FakePagingConstant
import com.boot.components.fake.FakePlaceHolderConfig
import com.boot.fake.model.FakeItem

class FakePagerFactory(private val fakeBackend: FakeBackend<FakeItem>) {
  fun buildPager(queryInput: String): Pager<Int, FakeItem> {
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
