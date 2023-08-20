package com.boot.dynamicfeature.repository

import android.app.Application
import com.boot.dynamicfeature.contract.DynamicFeature
import com.boot.dynamicfeature.logger.DynamicFeatureLogger
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class SplitInstallWrapper(
	private val installManager: SplitInstallManager
) {

	fun isFeatureInstall(feature: DynamicFeature): Boolean {
		return installManager.installedModules.contains(feature.moduleName)
	}

	fun installFeature(feature: DynamicFeature): Flow<SplitInstallSessionState> {
		return callbackFlow {
			val listener = SplitInstallStateUpdatedListener {
				launch { send(it) }
			}
			val request = SplitInstallRequest.newBuilder()
				.addModule(feature.moduleName)
				.build()

			var sessionId: Int? = null
			installManager.startInstall(request)
				.addOnSuccessListener { sessionId = it }
				.addOnFailureListener { DynamicFeatureLogger.log("Request to install failed $feature") }
				.addOnCompleteListener { DynamicFeatureLogger.log("Request to install module $feature") }

			installManager.registerListener(listener)
			awaitClose {
				val currentSessionId = sessionId
				if (currentSessionId != null) installManager.cancelInstall(currentSessionId)
				installManager.unregisterListener(listener)
			}
		}
	}
}

