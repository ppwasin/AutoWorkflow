package com.boot.scripts.cd.internal.release

import com.boot.scripts.cd.internal.release.ReleaseChannel.AppDistribution
import com.boot.scripts.cd.internal.release.ReleaseChannel.Firebase
import com.boot.scripts.cd.internal.shell

class ReleaseDistributor(private val appModule: String = "app:", versionName: String) {

  private val versionNameProp: String = "-PversionName=$versionName"
  fun release(channels: Set<ReleaseChannel>) {
    buildBundle()
    channels.forEach {
      when (it) {
        AppDistribution -> uploadOnAppDistribution()
        Firebase -> uploadOnGooglePlay()
      }
    }
  }

  private fun buildBundle() {
    shell("./gradlew ${appModule}bundleRelease $versionNameProp")
  }

  private fun uploadOnAppDistribution() {
    shell("./gradlew ${appModule}appDistributionUploadRelease $versionNameProp")
  }

  private fun uploadOnGooglePlay() {
    shell("./gradlew ${appModule}publishReleaseBundle --track alpha $versionNameProp")
  }
}
