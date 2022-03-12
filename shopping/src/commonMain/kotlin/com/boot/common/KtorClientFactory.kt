package com.boot.common

import com.soywiz.klock.seconds
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json


object KtorClientFactory {
    fun createHttpClient(engine: HttpClientEngine = CIO.create()) = HttpClient(engine) {
        install(HttpTimeout) {
            requestTimeoutMillis = 20.seconds.millisecondsLong
            socketTimeoutMillis = 20.seconds.millisecondsLong
            connectTimeoutMillis = 20.seconds.millisecondsLong
        }


        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}
