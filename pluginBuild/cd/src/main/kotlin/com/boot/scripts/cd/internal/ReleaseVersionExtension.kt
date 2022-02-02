package com.boot.scripts.cd.internal

typealias ReleaseVersionExt = ReleaseVersion.Companion

fun ReleaseVersionExt.initial(): ReleaseVersion {
  return ReleaseVersion(0, 0, 0)
}

fun ReleaseVersionExt.toString(version: ReleaseVersion): String {
  return version.run { "$major.$minor.$patch" }
}

fun ReleaseVersionExt.fromString(str: String): ReleaseVersion {
  val versions = str.split(".")
  return ReleaseVersion(
    major = versions.getOrNull(0)?.toIntOrNull() ?: 0,
    minor = versions.getOrNull(1)?.toIntOrNull() ?: 0,
    patch = versions.getOrNull(2)?.toIntOrNull() ?: 0
  )
}
