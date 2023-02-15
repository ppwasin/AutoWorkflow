package com.boot.fake.data

import com.boot.fake.model.PagingResponse
import kotlinx.coroutines.delay

class FakeBackend<T>(
  private val totalItems: Int,
  createFakeItem: (index: Int) -> T
) {
  private val allItems = (0..totalItems).map { index -> createFakeItem(index) }

  suspend fun getFakeItems(page: Int, perPage: Int): PagingResponse<T> {
    delay(1000)
    val startIndex = perPage * (page - 1)
    val lastIndex = perPage * page
    return PagingResponse(
        totalPages = totalItems / perPage,
        totalItems = totalItems,
        page = page,
        items = allItems.subList(fromIndex = startIndex, toIndex = lastIndex),
      )
      .also { println("get items at page $page => Result: $it") }
  }
}
