package com.boot.common

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get

class ShoppingApi(private val httpClient: HttpClient) : Api {
    override suspend fun fetchShoppingItems(): Either<DataError, List<ShoppingListItem>> {
        return Either.catch {
            httpClient.get<List<ShoppingListItem>>("http://localhost:8080/${ShoppingListItem.path}")
        }.mapLeft { exception ->
            when (exception) {
                is RedirectResponseException -> {
                    // Status codes 3XX
                    /*
                    val errorString = exception.response.receive<String>()
                    val jsonObject = JSONObject(errorString)
                    val errorMessage = jsonObject.getString("status_message")
                     */

                    ApiError.RedirectError
                }

                is ClientRequestException -> {
                    // status codes 4XX
                    ApiError.RequestError
                }

                is ServerResponseException -> {
                    // status codes 5XX
                    ApiError.ServerError
                }
                else -> ApiError.UnRecognize
            }
        }
    }
}