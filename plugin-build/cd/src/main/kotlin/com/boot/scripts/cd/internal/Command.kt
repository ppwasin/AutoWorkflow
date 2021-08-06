package com.boot.scripts.cd.internal

fun getLastTag(matchRegex: String): ReleaseVersion {
  val versions =
    runCatching {
        shell("git describe --tags --abbrev=0 --first-parent --match '$matchRegex'")
      }
      .getOrNull()
      ?.removePrefix(matchRegex)
      ?.split(".")

  return ReleaseVersion(
    major = versions?.getOrNull(0)?.toIntOrNull() ?: 0,
    minor = versions?.getOrNull(1)?.toIntOrNull() ?: 0,
    patch = versions?.getOrNull(2)?.toIntOrNull() ?: 0
  )
}