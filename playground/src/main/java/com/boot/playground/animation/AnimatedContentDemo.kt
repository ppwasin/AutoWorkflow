package com.boot.playground.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AnimatedContentDemo() {
	Row {
		var count by remember { mutableStateOf(0) }
		Button(onClick = { count++ }) {
			Text("Add")
		}
		AnimatedContent(targetState = count) { targetCount ->
			// Make sure to use `targetCount`, not `count`.
			Text(text = "Count: $targetCount")
		}
	}
}
