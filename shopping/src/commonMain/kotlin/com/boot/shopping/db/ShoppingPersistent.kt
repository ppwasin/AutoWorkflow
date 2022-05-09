package com.boot.shopping.db

import arrow.core.Either
import com.boot.shopping.CacheError
import com.boot.shopping.DataError
import com.boot.shopping.Persistent
import com.boot.shopping.network.ShoppingItemResponse

class ShoppingPersistent(database: ShoppingDatabase) : Persistent {
    private val itemQueries = database.shoppingQueries
    override suspend fun insertIntoPersistent(items: List<ShoppingItemResponse>): Either<DataError, Unit> {
        return Either
            .catch {
                itemQueries.transaction {
                    items.forEach {
                        itemQueries.insertItem(id = it.id, name = it.name, description = it.desc)
                    }
                }
            }.mapLeft { CacheError.InsertError }
    }

    override suspend fun getFromPersistent(): Either<DataError, List<ShoppingItemResponse>> {
        return Either
            .catch {
                itemQueries.selectAll().executeAsList()
                    .map {
                        ShoppingItemResponse(
                            id = it.id,
                            name = it.name,
                            desc = it.description
                        )
                    }
            }
            .mapLeft { CacheError.ReadError }
    }

    override suspend fun isEmpty(): Either<DataError, Boolean> {
        return Either
            .catch { !itemQueries.isEmpty().executeAsOne() }
            .mapLeft { CacheError.ReadError }
    }
}