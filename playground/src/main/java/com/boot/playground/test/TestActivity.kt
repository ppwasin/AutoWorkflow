package com.boot.playground.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

object TestActivityDependencies {
	var dispatcherProvider: DispatcherProvider<CoroutineDispatcher> =
		DispatcherProvider(
			dispatcherIO = Dispatchers.IO,
			dispatcherMain = Dispatchers.Main,
			dispatcherDefault = Dispatchers.Default,
		)
}

class TestFirstActivity : ComponentActivity() {
	val viewModel by viewModels<TestFirstViewModel>()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.start()

		TestLogger.log("lifecycleScope.launch outside")
		lifecycleScope.launch {
			TestLogger.log("lifecycleScope.launch inside start delay")
			viewModel.state.filter { it == 1 }.first()

			TestLogger.log("lifecycleScope.launch inside end delay")
			startActivity(
				Intent(
					this@TestFirstActivity,
					TestSecondActivity::class.java,
				),
			)
			finish()
			TestLogger.log("lifecycleScope.launch inside after startactivity")
		}
	}
}

class TestFirstViewModel : ViewModel() {
	val state = MutableStateFlow(0)
	fun start() =
		viewModelScope.launch(
			TestActivityDependencies.dispatcherProvider.dispatcherIO,
		) {
			delay(5000)
			state.value = 1
		}
}
