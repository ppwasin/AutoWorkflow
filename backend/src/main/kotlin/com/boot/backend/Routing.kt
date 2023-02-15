package com.boot.backend

import com.boot.shopping.network.ShoppingItemResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

val shoppingList = mutableListOf(
	ShoppingItemResponse(id = 1, name = "Cucumbers 🥒", desc = "item number 1"),
	ShoppingItemResponse(id = 2, name = "Tomatoes 🍅", desc = "item number 2"),
	ShoppingItemResponse(id = 3, name = "Orange Juice 🍊", desc = "item number 3"),
)

fun Routing.shoppingRoutes() {
	route(ShoppingItemResponse.path) {
		get {
			call.respond(shoppingList)
		}
		post {
			shoppingList += call.receive<ShoppingItemResponse>()
			call.respond(HttpStatusCode.OK)
		}
		delete("/{id}") {
			val id = call.parameters["id"]?.toLong() ?: error("Invalid delete request")
			shoppingList.removeIf { it.id == id }
			call.respond(HttpStatusCode.OK)
		}
	}
}
