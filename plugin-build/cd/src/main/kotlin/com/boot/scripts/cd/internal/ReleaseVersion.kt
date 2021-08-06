package com.boot.scripts.cd.internal

data class ReleaseVersion(val major: Int, val minor: Int, val patch: Int) {

  fun asDotVersion(): String {
    return "$major.$minor.$patch"
  }
}
