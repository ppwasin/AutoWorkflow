package com.boot.shopping

sealed interface DataError
sealed interface ApiError: DataError {
    object RedirectError: ApiError
    object RequestError: ApiError
    object ServerError: ApiError
    object UnRecognize: ApiError
}
sealed interface CacheError: DataError {
    object InsertError: CacheError
    object ReadError: CacheError
}