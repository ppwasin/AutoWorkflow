package com.boot.components.search.action

sealed interface PagingResult {
  object Idle : PagingResult
  sealed interface Loading : PagingResult {
    object FirstPage : Loading
    object Loadmore : Loading
  }
  sealed interface Error : PagingResult {}

  sealed interface Result : PagingResult {
    data class Error(val keyword: String, val errorMsg: String) : Result
    data class Content<T>(val keyword: String, val content: List<T>) : Result
  }
}
