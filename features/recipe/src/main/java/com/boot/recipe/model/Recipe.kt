package com.boot.recipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
	@PrimaryKey val id: Long,
	val name: String,
	val type: String,
	val logo: String?,
)
