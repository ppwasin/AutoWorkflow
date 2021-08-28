package com.boot.components.fake

import com.boot.fake.model.FakeItem
import com.boot.fake.model.PagingResponse

object FakePlaceHolderConfig {
  const val isEnable: Boolean = true
  fun getBefore(response: PagingResponse<FakeItem>): Int {
    return response.items.minOf { it.id }
  }

  fun getAfter(perPage: Int, response: PagingResponse<FakeItem>): Int {
    return response.totalItems - (perPage * response.page)
  }
}
