package com.boot.backend

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
	embeddedServer(CIO, port = 8080, watchPaths = listOf("classes")) {
		install(ContentNegotiation) {
			json()
		}
		install(CORS) {
			method(Get)
			method(Post)
			method(Delete)
			anyHost()
		}
		install(Compression) { gzip() }
		install(CallLogging)

		routing {
			get("/") {
				call.respondText("Hello")
			}
			get("/xyz") {
				call.respondText("Hello, world!")
			}
			shoppingRoutes()
		}
	}.start(wait = true)
}
