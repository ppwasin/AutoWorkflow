package com.boot.recipe

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.boot.recipe.db.RecipeDatabase
import com.boot.recipe.model.Recipe

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
  private val query: String,
  private val service: RecipeService,
  private val repoDatabase: RecipeDatabase
) : RemoteMediator<Int, Recipe>() {

  override suspend fun load(loadType: LoadType, state: PagingState<Int, Recipe>): MediatorResult {
    val page =
      when (loadType) {
        LoadType.REFRESH -> {
          // TODO
        }
        LoadType.PREPEND -> {
          // TODO
        }
        LoadType.APPEND -> {
          // TODO
        }
      }
    val apiQuery = query + IN_QUALIFIER

    try {
      val apiResponse = service.searchRepos(apiQuery, page, state.config.pageSize)

      val repos = apiResponse.items
      val endOfPaginationReached = repos.isEmpty()
      repoDatabase.withTransaction {
        // clear all tables in the database
        if (loadType == LoadType.REFRESH) {
          repoDatabase.remoteKeysDao().clearRemoteKeys()
          repoDatabase.reposDao().clearRepos()
        }
        val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
        val nextKey = if (endOfPaginationReached) null else page + 1
        val keys = repos.map { RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey) }
        repoDatabase.remoteKeysDao().insertAll(keys)
        repoDatabase.reposDao().insertAll(repos)
      }
      return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (exception: IOException) {
      return MediatorResult.Error(exception)
    } catch (exception: HttpException) {
      return MediatorResult.Error(exception)
    }
  }
}
