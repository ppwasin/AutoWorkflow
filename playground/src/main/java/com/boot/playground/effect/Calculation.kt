package com.boot.playground.effect

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.boot.playground.performance.recomposeHighlighter

@Composable
fun Calculation(input: Int) {
  val rememberUpdatedStateInput by rememberUpdatedState(input)
  val rememberWithMutableStateOf by remember { mutableStateOf(input) }
  val rememberedInput = remember { input }

  Text(
    modifier = Modifier.recomposeHighlighter(),
    text = "rememberUpdatedState: $rememberUpdatedStateInput",
  )
  Text(
    modifier = Modifier.recomposeHighlighter(),
    text = "rememberedInput: $rememberedInput",
  )
  Text(
    modifier = Modifier.recomposeHighlighter(),
    text = "rememberWithMutableStateOf: $rememberWithMutableStateOf",
  )
}
