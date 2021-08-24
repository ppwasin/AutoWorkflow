package com.boot.components.fake

data class PagingResponse<T>(
  val totalPages: Int,
  val totalItems: Int,
  val items: List<T>,
  val page: Int
)
