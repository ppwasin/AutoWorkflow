package com.boot.scripts.cd.internal

fun getLastTagString(matchRegex: String): String {
  val tagVersionString =
    runCatching { shell("git describe --tags --abbrev=0 --first-parent --match '$matchRegex'") }
      .getOrNull()
  return if (tagVersionString.isNullOrEmpty()) {
    val initialVersion = ReleaseVersion.initial()
    println("[Warn] No tag found use initial version: $initialVersion")
    return initialVersion.toString()
  } else tagVersionString
}
