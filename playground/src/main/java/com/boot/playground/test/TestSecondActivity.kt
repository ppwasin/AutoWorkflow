package com.boot.playground.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp

class TestSecondActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		ActivityObserver.currentActivityName = "TestSecondActivity"
		setContent {
			Text(
				modifier =
				Modifier
					.padding(36.dp)
					.semantics { testTag = TAG_SAMPLE_TEXT },
				text = "Sample text",
			)
		}
	}

	companion object {
		const val TAG_SAMPLE_TEXT = "TAG_SAMPLE_TEXT"
	}
}
