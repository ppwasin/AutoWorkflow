package com.boot.entrypoint.ui.bottomNav

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NextPlan
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.ui.graphics.vector.ImageVector
import com.boot.entrypoint.platform.BottomNavInfoGetter
import java.util.*

fun MainScreenItems.Companion.bottomNav():
	BottomNavInfoGetter<MainScreenItems> {
	return object : BottomNavInfoGetter<MainScreenItems> {
		override fun getRoute(item: MainScreenItems): String = item.name.lowercase()
		override fun getLabel(item: MainScreenItems): String =
			item.name.replaceFirstChar {
				if (it.isLowerCase()) it.titlecase(Locale.getDefault())
				else it.toString()
			}

		override fun getIcon(item: MainScreenItems): ImageVector {
			return when (item) {
				MainScreenItems.Recipes -> Filled.Favorite
				MainScreenItems.Plan -> Filled.NextPlan
				MainScreenItems.Basket -> Filled.ShoppingBasket
				MainScreenItems.Profile -> Filled.Person
			}
		}
	}
}
