package com.boot.mealplan.recipes

sealed interface RecipeSearchAction {
  data class Searching(override val keyword: String) : RecipeSearchAction
  data class Error(override val keyword: String, val errorMsg: String) : RecipeSearchAction
  data class Content(override val keyword: String, val content: List<RecipeContent>) :
    RecipeSearchAction
  val keyword: String
}

data class RecipeContent(val id: Int, val title: String, val rating: Int, val type: String)
