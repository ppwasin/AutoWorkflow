package com.boot.common.db

import arrow.core.Either
import com.boot.common.CacheError
import com.boot.common.DataError
import com.boot.common.Persistent
import com.boot.common.ShoppingListItem
import com.boot.shopping.db.ShoppingDatabase

class ShoppingPersistent(database: ShoppingDatabase) : Persistent {
    private val itemQueries = database.shoppingQueries
    override suspend fun insertIntoPersistent(items: List<ShoppingListItem>): Either<DataError, Unit> {
        return Either
            .catch {
                itemQueries.transaction {
                    items.forEach {
                        itemQueries.insertItem(id = it.id, name = it.name, description = it.desc)
                    }
                }
            }.mapLeft { CacheError.InsertError }
    }

    override suspend fun getFromPersistent(): Either<DataError, List<ShoppingListItem>> {
        return Either
            .catch {
                itemQueries.selectAll().executeAsList()
                    .map { ShoppingListItem(id = it.id, name = it.name, desc = it.description) }
            }
            .mapLeft { CacheError.ReadError }
    }

    override suspend fun isEmpty(): Either<DataError, Boolean> {
        return Either
            .catch { !itemQueries.isEmpty().executeAsOne() }
            .mapLeft { CacheError.ReadError }
    }
}