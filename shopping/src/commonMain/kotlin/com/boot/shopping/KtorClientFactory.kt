package com.boot.shopping

import com.soywiz.klock.seconds
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
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
