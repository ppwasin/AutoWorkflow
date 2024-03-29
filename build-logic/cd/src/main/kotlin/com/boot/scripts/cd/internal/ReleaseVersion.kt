package com.boot.scripts.cd.internal

data class ReleaseVersion(val major: Int, val minor: Int, val patch: Int) {
	fun increaseMinor(): ReleaseVersion {
		return copy(minor = minor + 1, patch = 0)
	}

	fun increasePatch(): ReleaseVersion {
		return copy(patch = patch + 1)
	}

	override fun toString(): String {
		return ReleaseVersion.toString(this)
	}

	companion object
}


