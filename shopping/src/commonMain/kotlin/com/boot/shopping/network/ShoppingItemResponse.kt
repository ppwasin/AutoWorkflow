package com.boot.shopping.network

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItemResponse(
    val id: Long,
    val name: String,
    val desc: String
) {

    companion object {
        const val path = "shoppingList"
    }
}