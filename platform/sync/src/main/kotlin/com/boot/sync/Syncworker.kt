package com.boot.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters

class Syncworker(
	private val appContext: Context,
	workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {
	override suspend fun doWork(): Result {
		TODO("Not yet implemented")
	}

	companion object {

		private val SyncConstraints
			get() =
				Constraints.Builder()
					.setRequiredNetworkType(NetworkType.CONNECTED)
					.build()

		/** Expedited one time work to sync data on app startup */
		fun startUpSyncWork() =
			OneTimeWorkRequestBuilder<Syncworker>()
				.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
				.setConstraints(SyncConstraints)
				.build()
	}
}

/**
 * * private fun createOneTimeWorkRequest() { // 1 val imageWorker =
 * OneTimeWorkRequestBuilder<ImageDownloadWorker>() .setConstraints(constraints)
 * .addTag("imageWork") .build() // 2 workManager.enqueueUniqueWork(
 * "oneTimeImageDownload", ExistingWorkPolicy.KEEP, imageWorker ) }
 *
 * activityHomeBinding.btnImageDownload.setOnClickListener {
 * showLottieAnimation() activityHomeBinding.downloadLayout.visibility =
 * View.GONE createOneTimeWorkRequest() }
 *
 * private fun observeWork(id: UUID) { // 1
 * workManager.getWorkInfoByIdLiveData(id) .observe(this, { info -> // 2 if
 * (info != null && info.state.isFinished) { hideLottieAnimation()
 * activityHomeBinding.downloadLayout.visibility = View.VISIBLE // 3 val
 * uriResult = info.outputData.getString("IMAGE_URI") if (uriResult != null) {
 * showDownloadedImage(uriResult.toUri()) } } }) }
 */
