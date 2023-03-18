package com.boot.recipe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boot.recipe.dao.RecipeDao
import com.boot.recipe.dao.RemoteKeysDao
import com.boot.recipe.model.Recipe
import com.boot.recipe.model.RemoteKeys

@Database(
	entities = [Recipe::class, RemoteKeys::class],
	version = 1,
	exportSchema = false,
)
abstract class RecipeDatabase : RoomDatabase() {
	abstract fun recipeDao(): RecipeDao
	abstract fun remoteKeysDao(): RemoteKeysDao

	companion object {

		@Volatile
		private var INSTANCE: RecipeDatabase? = null

		fun getInstance(context: Context): RecipeDatabase =
			INSTANCE
				?: synchronized(this) {
					INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
				}

		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(
				context.applicationContext,
				RecipeDatabase::class.java,
				"recipe.db",
			)
				.fallbackToDestructiveMigration()
				.build()
	}
}
