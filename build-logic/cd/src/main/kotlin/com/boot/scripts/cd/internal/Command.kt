package com.boot.scripts.cd.internal

fun getCurrentVersion(matchRegex: String): ReleaseVersion {
	println("Fetch tags from origin")
	runCatching { shell("git fetch --unshallow --tags") }
		.onFailure { shell("git fetch --tags") }

	val tagVersionString = runCatching {
		shell(buildLastTagCmd(matchRegex))
	}.getOrNull()

	return if (tagVersionString.isNullOrEmpty()) {
		return ReleaseVersion.initial().also { println("[Warn] No tag found use initial version: $it") }
	} else ReleaseVersion.fromString(tagVersionString)
}

private fun buildLastTagCmd(matchRegex: String): String {
	return "git describe --tags --abbrev=0 --first-parent --match \"$matchRegex\""
}
