package com.boot.dynamicfeature.repository

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.boot.dynamicfeature.contract.DynamicFeature
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class SplitInstallWrapperTest {

	@Test
	fun testLoadFeature() {
		val context = ApplicationProvider.getApplicationContext<Application>()
		val installManager = FakeSplitInstallManagerFactory.create(
			context,
			context.getExternalFilesDir("local_testing"),
		)
		installManager.setShouldNetworkError(true)

		val target = SplitInstallWrapper(installManager)

		runBlocking {
			target.installFeature(DynamicFeature("test"))
				.first()
		}
	}
}
