package com.boot.recipe

interface RecipeService {

	// @GET("search/repositories?sort=stars")
	fun searchRepos(apiQuery: String, page: Unit, pageSize: Int): Any
}
