package com.boot.shopping

import arrow.core.Either
import arrow.core.computations.either
import com.boot.shopping.network.ShoppingItemResponse

interface Api {
	suspend fun fetchShoppingItems(): Either<DataError, List<ShoppingItemResponse>>
}

interface Persistent {
	suspend fun insertIntoPersistent(items: List<ShoppingItemResponse>): Either<DataError, Unit>
	suspend fun getFromPersistent(): Either<DataError, List<ShoppingItemResponse>>
	suspend fun isEmpty(): Either<DataError, Boolean>
}

suspend fun <R> R.getShoppingItems(): Either<DataError, List<ShoppingItemResponse>>
	where R : Api,
				R : Persistent = either<DataError, List<ShoppingItemResponse>> {
	val isEmptyCache = isEmpty().bind()
	println("is database empty: $isEmptyCache")
	if (isEmpty().bind()) {
		val items: List<ShoppingItemResponse> = fetchShoppingItems().bind()
		println("network result: $items")
		insertIntoPersistent(items).bind()
		println("save into database")
	}
	getFromPersistent().bind()
}.tapLeft { println("error: $it") }
