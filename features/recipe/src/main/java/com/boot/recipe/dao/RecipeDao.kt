package com.boot.recipe.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boot.recipe.model.Recipe

@Dao
interface RecipeDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertAll(repos: List<Recipe>)

  @Query("SELECT * FROM recipe WHERE name LIKE :queryString ORDER BY name ASC")
  fun queryByName(queryString: String): PagingSource<Int, Recipe>

  @Query("DELETE FROM recipe") suspend fun clear()
}
