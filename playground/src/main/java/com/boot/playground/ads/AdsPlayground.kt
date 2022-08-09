package com.boot.playground.ads

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AdsPlayground(viewModel: AdsViewModel) {
  val adsId = viewModel.adsId

  Column {
    Text("AdsId: $adsId")
    Button(onClick = viewModel::fetchId) { Text("Refresh") }
  }
}
