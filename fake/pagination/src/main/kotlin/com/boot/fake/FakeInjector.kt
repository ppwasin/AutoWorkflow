package com.boot.fake

import com.boot.components.fake.FakePagingConstant
import com.boot.fake.data.FakeBackend
import com.boot.fake.data.FakePagerFactory
import com.boot.fake.model.FakeItem

object FakeInjector {
  fun createPagerFactory() = FakePagerFactory(createBackend())
  private fun createBackend() =
    FakeBackend(FakePagingConstant.TOTAL_ITEMS) { index ->
      val page = (index / FakePagingConstant.PER_PAGE) + 1
      FakeItem(id = index, text = "index: $index on page $page")
    }
}
