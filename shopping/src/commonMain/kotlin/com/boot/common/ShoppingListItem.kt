package com.boot.common

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListItem(
    val id: Long,
    val name: String,
    val desc: String
) {
//    val id: Long = desc.hashCode().toLong()

    companion object {
        const val path = "shoppingList"
    }
}