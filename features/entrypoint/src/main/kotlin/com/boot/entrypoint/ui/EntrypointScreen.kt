/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.boot.entrypoint.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.boot.entrypoint.platform.BtmSlot
import com.boot.entrypoint.ui.bottomNav.MainScreenItems
import com.boot.entrypoint.ui.bottomNav.bottomNav
import com.boot.mealplan.recipes.RecipeEntrypoint
import com.boot.theme.material.AppMaterialTheme

@Composable
fun EntrypointScreen() {
  // hoist navctrl here to save/restore navigation state when swap between BTN
  val recipeNavController = rememberNavController()
  AppMaterialTheme {
    BtmSlot(
      screenItems = MainScreenItems.values().toSet(),
      infoGetter = MainScreenItems.bottomNav(),
      navigateTo = { item ->
        when (item) {
          MainScreenItems.Recipes -> RecipeEntrypoint(recipeNavController)
          else -> Text(item.name)
        }
      }
    )
  }
}
