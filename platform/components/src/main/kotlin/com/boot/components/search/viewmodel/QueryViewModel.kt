package com.boot.components.search.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn

class QueryViewModel<QueryInput : Any, Output : Any>(
  private val pagerFactory: (QueryInput) -> Pager<Int, Output>,
  scope: CoroutineScope,
  initialQuery: QueryInput
) {
  val state: Flow<PagingData<Output>>
  val query: MutableState<QueryInput> = mutableStateOf(initialQuery)

  init {
    state =
      snapshotFlow { query.value }
        .debounce { if (it == initialQuery) 0 else 400 }
        .flatMapLatest { queryInput ->
          pagerFactory(queryInput).flow.cachedIn(scope)
        }
        .shareIn(
          scope = scope,
          started = SharingStarted.WhileSubscribed(3000),
          replay = 1
        )
  }
}
