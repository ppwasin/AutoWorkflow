package com.boot.scripts.cd.internal

fun getLastTagString(matchRegex: String): String {
  return shell("git describe --tags --abbrev=0 --first-parent --match '$matchRegex'")
}
