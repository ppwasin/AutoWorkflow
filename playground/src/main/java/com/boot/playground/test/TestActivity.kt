package com.boot.playground.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
