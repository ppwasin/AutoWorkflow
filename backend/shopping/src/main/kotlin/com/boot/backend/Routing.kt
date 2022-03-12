package com.boot.backend

import com.boot.common.ShoppingListItem
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import java.util.UUID

val shoppingList = mutableListOf(
    ShoppingListItem(id = 1, name = "Cucumbers 🥒", desc = "item number 1"),
    ShoppingListItem(id = 2, name = "Tomatoes 🍅", desc = "item number 2"),
    ShoppingListItem(id = 3, name = "Orange Juice 🍊", desc = "item number 3")
)

fun Application.registerShoppingRoutes() {
    routing {
        shoppingRoutes()
    }
}

fun Routing.shoppingRoutes() {
    route(ShoppingListItem.path) {
        get {
            call.respond(shoppingList)
        }
        post {
            shoppingList += call.receive<ShoppingListItem>()
            call.respond(HttpStatusCode.OK)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toLong() ?: error("Invalid delete request")
            shoppingList.removeIf { it.id == id }
            call.respond(HttpStatusCode.OK)
        }
    }
}
