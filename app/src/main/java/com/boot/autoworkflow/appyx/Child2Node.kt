package com.boot.autoworkflow.appyx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import kotlin.time.Duration
import kotlinx.coroutines.delay

class Child2Node(buildContext: BuildContext, private val onClick: () -> Unit) :
  Node(buildContext) {

  @Composable
  override fun View(modifier: Modifier) {
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
      while (true) {
        ++count
        delay(Duration.run { 1.seconds })
      }
    }
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxSize(),
    ) {
      Text(
        text = "Child 2 -> $count",
        fontSize = 36.sp,
        modifier = Modifier.padding(bottom = 16.dp),
      )
      Text(
        text = "(now hit the back button)",
        fontSize = 12.sp,
      )

      Button(
        onClick = { onClick() }, // <- Don't forget to use the callback
      ) { Text(text = "Press here to navigate") }
    }
  }
}
