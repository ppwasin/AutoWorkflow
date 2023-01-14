package com.boot.playground.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ActivityObserver {
  var currentActivityName: String = ""
}

object DispatcherProvider {
  val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
  val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
  var dispatcherMain: CoroutineDispatcher = Dispatchers.Main
}

class TestFirstActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //    startActivity(
    //      Intent(
    //        this@TestFirstActivity,
    //        TestSecondActivity::class.java,
    //      ),
    //    )

    lifecycleScope.launch(DispatcherProvider.dispatcherMain) {
      delay(5000)
      startActivity(
        Intent(
          this@TestFirstActivity,
          TestSecondActivity::class.java,
        ),
      )
    }
  }
}

class TestSecondActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ActivityObserver.currentActivityName = "TestSecondActivity"
    setContent {
      Text(
        modifier =
          Modifier.padding(36.dp).semantics { testTag = TAG_SAMPLE_TEXT },
        text = "Sample text",
      )
    }
  }

  companion object {
    const val TAG_SAMPLE_TEXT = "TAG_SAMPLE_TEXT"
  }
}
