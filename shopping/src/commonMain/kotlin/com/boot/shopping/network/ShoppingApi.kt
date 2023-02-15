package com.boot.shopping.network

import arrow.core.Either
import com.boot.shopping.Api
import com.boot.shopping.ApiError
import com.boot.shopping.DataError
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class ShoppingApi(private val httpClient: HttpClient) : Api {
	override suspend fun fetchShoppingItems(): Either<DataError, List<ShoppingItemResponse>> {
		return Either.catch {
			httpClient.get<List<ShoppingItemResponse>>("http://localhost:8080/${ShoppingItemResponse.path}")
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
