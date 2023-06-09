package com.boot.dynamicfeature.adapter

import android.app.Application
import com.boot.dynamicfeature.logger.DynamicFeatureLogger
import com.boot.dynamicfeature.provider.SplitInstallState
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch


//fun SplitInstallerSyntax.isFeatureInstalled(feature: Feature<*>): Boolean {
//	return splitInstallManager.installedModules.contains(feature.moduleName)
//}

class SplitInstallWrapper(private val application: Application) {
	private val installManager by lazy { SplitInstallManagerFactory.create(application) }

	fun isFeatureInstall(moduleName: String): Boolean {
		DynamicFeatureLogger.log(installManager.installedModules.toString())
		return installManager.installedModules.contains(moduleName)
	}

	fun installFeature(moduleName: String): Flow<SplitInstallState> {
		return callbackFlow {
			val listener = observeStateCallback {
				launch { send(it) }
			}
			val request = SplitInstallRequest.newBuilder()
				.addModule(moduleName)
				.build()

			var sessionId: Int? = null
			installManager.startInstall(request)
				.addOnSuccessListener { sessionId = it }
				.addOnFailureListener { launch { send(SplitInstallState.Error(it)) } }
				.addOnCompleteListener { DynamicFeatureLogger.log("Install Module $moduleName complete") }

			installManager.registerListener(listener)
			awaitClose {
				val currentSessionId = sessionId
				if (currentSessionId != null) installManager.cancelInstall(currentSessionId)
				installManager.unregisterListener(listener)
			}
		}
	}

	private fun observeStateCallback(callback: (SplitInstallState) -> Unit): SplitInstallStateUpdatedListener {
		return SplitInstallStateUpdatedListener { state ->
			when (state.status()) {
				SplitInstallSessionStatus.DOWNLOADING -> callback(
					SplitInstallState.InProgress(
						SplitInstallState.MainState.Download,
						state,
					),
				)

				SplitInstallSessionStatus.DOWNLOADED -> callback(
					SplitInstallState.Done(
						SplitInstallState.MainState.Download,
						state,
					),
				)

				SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> callback(
					SplitInstallState.RequireUserConfirmation(
						state,
					),
				)

				SplitInstallSessionStatus.INSTALLING -> callback(
					SplitInstallState.InProgress(
						SplitInstallState.MainState.Install,
						state,
					),
				)

				SplitInstallSessionStatus.INSTALLED -> callback(
					SplitInstallState.Done(
						SplitInstallState.MainState.Install,
						state,
					),
				)

				SplitInstallSessionStatus.FAILED -> callback(SplitInstallState.Failed(state))
				SplitInstallSessionStatus.CANCELING -> callback(
					SplitInstallState.InProgress(
						SplitInstallState.MainState.Cancel,
						state,
					),
				)

				SplitInstallSessionStatus.CANCELED -> callback(
					SplitInstallState.Done(
						SplitInstallState.MainState.Cancel,
						state,
					),
				)

				SplitInstallSessionStatus.PENDING -> callback(SplitInstallState.Pending(state))
				SplitInstallSessionStatus.UNKNOWN -> callback(SplitInstallState.Unknown(state))
			}
		}

	}
}

