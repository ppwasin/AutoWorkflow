package com.boot.shopping.di

import com.boot.shopping.Api
import com.boot.shopping.Persistent
import com.boot.shopping.db.ShoppingDatabase
import com.boot.shopping.db.ShoppingPersistent
import com.boot.shopping.network.ShoppingApi
import io.ktor.client.*

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