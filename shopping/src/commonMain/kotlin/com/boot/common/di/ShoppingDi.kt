package com.boot.common.di

import com.boot.common.*
import com.boot.common.db.ShoppingPersistent
import com.boot.shopping.db.ShoppingDatabase
import io.ktor.client.HttpClient

class ShoppingDi(
    private val api: Api,
    private val persistent: Persistent
) : Api by api, Persistent by persistent {

    companion object {
        operator fun invoke(httpClient: HttpClient, database: ShoppingDatabase): ShoppingDi {
            return ShoppingDi(ShoppingApi(httpClient), ShoppingPersistent(database))
        }
    }
}