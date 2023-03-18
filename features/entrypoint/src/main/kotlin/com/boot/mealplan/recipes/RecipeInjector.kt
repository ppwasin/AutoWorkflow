package com.boot.mealplan.recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.boot.components.search.viewmodel.QueryViewModel
import com.boot.fake.FakeInjector
import com.boot.fake.model.FakeItem

object RecipeInjector {
	private val pagerFactory = FakeInjector.createPagerFactory()

	@Composable
	fun rememberQueryViewModel(): QueryViewModel<String, FakeItem> {
		val coroutineScope = rememberCoroutineScope()
		return remember {
			QueryViewModel(
				pagerFactory = pagerFactory::buildPager,
				scope = coroutineScope,
				initialQuery = "",
			)
		}
	}
}
