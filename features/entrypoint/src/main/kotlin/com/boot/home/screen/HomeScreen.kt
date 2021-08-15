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
package com.boot.home.screen

import androidx.compose.material.BottomAppBar
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(versionDescription: String) {
  val materialBlue700 = Color(0xFF1976D2)
  val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
  Scaffold(
    scaffoldState = scaffoldState,
    topBar = { TopAppBar(title = { Text("Home") }, backgroundColor = materialBlue700) },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = { FloatingActionButton(onClick = {}) { Text("X") } },
    drawerContent = { Text(text = "drawerContent") },
    content = { Text("Running on version $versionDescription") },
    bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
  )
}

@Preview
@Composable
fun HomeScreenPreview() {
  HomeScreen("")
}
