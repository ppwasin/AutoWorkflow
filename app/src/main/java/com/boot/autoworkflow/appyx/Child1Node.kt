package com.boot.autoworkflow.appyx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class Child1Node(
  buildContext: BuildContext,
  private val onButtonPressed:
    () -> Unit // <- This is how we'll trigger a change in the parent
) : Node(buildContext) {

  @Composable
  override fun View(modifier: Modifier) {
    val dummyItems = remember { (0..10).toList() }
    Column {
      Button(
        onClick = { onButtonPressed() },
      ) { Text(text = "Press here to navigate") }
      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
      ) {
        items(dummyItems) { item ->
          Surface(shadowElevation = 6.dp) {
            Row(Modifier.padding(60.dp)) {
              Text(item.toString())
              Text(item.toString())
              Text(item.toString())
            }
          }
        }
      }
    }

    //    Box(
    //      modifier = modifier
    //        .fillMaxSize()
    //    ) {
    //      Column(
    //        verticalArrangement = Arrangement.Center,
    //        horizontalAlignment = Alignment.CenterHorizontally,
    //        modifier = Modifier
    //          .fillMaxSize()
    //      ) {
    //        Text(
    //          text = "Child 1",
    //          fontSize = 36.sp,
    //          modifier = Modifier.padding(bottom = 16.dp)
    //        )
    //        Button(
    //          onClick = { onButtonPressed() }, // <- Don't forget to use the callback
    //        ) {
    //          Text(text = "Press here to navigate")
    //        }
    //      }
    //    }
  }
}
