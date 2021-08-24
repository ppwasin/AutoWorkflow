package com.boot.components.fake

import com.boot.components.search.FakeSearchFetcher.FakeItem

object FakePlaceHolderConfig {
  const val isEnable: Boolean = true
  fun getBefore(response: PagingResponse<FakeItem>): Int {
    return response.items.minOf { it.id }
  }

  fun getAfter(perPage: Int, response: PagingResponse<FakeItem>): Int {
    return response.totalItems - (perPage * response.page)
  }
}
