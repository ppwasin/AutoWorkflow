package com.boot.components.search.action

sealed interface SearchAction {
  object Idle : SearchAction
  data class Searching(val keyword: String) : SearchAction
  sealed interface Result : SearchAction {
    data class Error(val keyword: String, val errorMsg: String) : Result
    data class Content<T>(val keyword: String, val content: List<T>) : Result
  }
}
