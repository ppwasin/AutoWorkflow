package com.boot.common

import arrow.core.Either
import arrow.core.computations.either

interface Api {
    suspend fun fetchShoppingItems(): Either<DataError, List<ShoppingListItem>>
}

interface Persistent {
    suspend fun insertIntoPersistent(items: List<ShoppingListItem>): Either<DataError, Unit>
    suspend fun getFromPersistent(): Either<DataError, List<ShoppingListItem>>
    suspend fun isEmpty(): Either<DataError, Boolean>
}

suspend fun <R> R.getShoppingItems(): Either<DataError, List<ShoppingListItem>>
        where R : Api,
              R : Persistent = either {
    val isEmptyCache = isEmpty().bind()
    println("is database empty: $isEmptyCache")
    if (isEmpty().bind()) {
        val items: List<ShoppingListItem> = fetchShoppingItems().bind()
        println("network result: $items")
        insertIntoPersistent(items).bind()
        println("save into database")
    }
    getFromPersistent().bind()
}