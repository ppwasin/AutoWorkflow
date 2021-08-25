package com.boot.components.search.data

import androidx.paging.Pager

interface QueryRepository<QueryInput, Output : Any> {
  fun buildPager(queryInput: QueryInput): Pager<Int, Output>
}
