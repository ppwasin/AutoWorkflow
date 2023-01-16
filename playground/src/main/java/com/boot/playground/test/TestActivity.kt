package com.boot.playground.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object TestActivityDependencies{
  lateinit var dispatcherProvider: DispatcherProvider<CoroutineDispatcher>
}
class TestFirstActivity : ComponentActivity() {
  val viewModel by viewModels<TestFirstViewModel>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel.start()

    TestLogger.log("lifecycleScope.launch outside")
    lifecycleScope.launch(TestActivityDependencies.dispatcherProvider.dispatcherMain) {
//      whenStarted {
        TestLogger.log("lifecycleScope.launch inside start delay")
        viewModel.state
          .filter { it == 1 }
          .first()

//        delay(10000)

        TestLogger.log("lifecycleScope.launch inside end delay")
        startActivity(
          Intent(
            this@TestFirstActivity,
            TestSecondActivity::class.java,
          ),
        )
        TestLogger.log("lifecycleScope.launch inside after startactivity")
      }

//    }
  }
}

class TestFirstViewModel: ViewModel(){
  val state = MutableStateFlow(0)
  fun start() = viewModelScope.launch(TestActivityDependencies.dispatcherProvider.dispatcherIO) {
    delay(5000)
    state.value = 1
  }
}
