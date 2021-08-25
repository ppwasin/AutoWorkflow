package com.boot.components.search.model

import androidx.paging.PagingData

data class SearchUiState<QueryInput : Any, Output : Any>(
  val queryInput: QueryInput,
  val pagingData: PagingData<Output>
)
