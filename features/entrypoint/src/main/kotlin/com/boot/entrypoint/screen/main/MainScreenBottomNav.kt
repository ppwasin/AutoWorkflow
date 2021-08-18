package com.boot.entrypoint.screen.main

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NextPlan
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.boot.entrypoint.platform.BottomNavTypeClasses
import com.boot.entrypoint.screen.Recipes
import java.util.Locale

fun MainScreenItems.Companion.bottomNav(): BottomNavTypeClasses<MainScreenItems> {
  return object : BottomNavTypeClasses<MainScreenItems> {
    override fun MainScreenItems.route(): String = name.lowercase()
    override fun MainScreenItems.label(): String =
      name.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
      }

    override fun MainScreenItems.icon(): ImageVector {
      return when (this) {
        MainScreenItems.Recipes -> Filled.Favorite
        MainScreenItems.Plan -> Filled.NextPlan
        MainScreenItems.Basket -> Filled.ShoppingBasket
        MainScreenItems.Profile -> Filled.Person
      }
    }

    @Composable
    override fun MainScreenItems.Screen() {
      return when (this) {
        MainScreenItems.Recipes -> Recipes()
        else -> Text(name)
      }
    }
  }
}
