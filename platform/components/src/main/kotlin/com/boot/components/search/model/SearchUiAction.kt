package com.boot.components.search.model

sealed interface SearchUiAction<QueryInput : Any> {
  data class Scroll<QueryInput : Any>(val queryInput: QueryInput) : SearchUiAction<QueryInput>
  data class Search<QueryInput : Any>(val queryInput: QueryInput) : SearchUiAction<QueryInput>
}
