package com.boot.playground.algorithm.unorganize

fun solution(S: String): Int {
	val sb = StringBuilder(S)
	val charMatcher = CharMatcher("BALLOON")

	var matchIndex: Int?
	while (sb.isNotEmpty()) {
		matchIndex = sb.indexOfFirst { charMatcher.matchNext(it) }
		println("matchIndex: $matchIndex")
		if (matchIndex == -1) break
		sb.deleteCharAt(matchIndex)
		println("newS: $sb ===")
	}

	return charMatcher.getNumberOfMatch()
}

fun solution2(S: String): Int {
	val stringMapHelper = StringMapHelper(S)
	val charMatcher = CharMatcher("BALLOON")

	while (stringMapHelper.contain(charMatcher.getCurrent())) {
		stringMapHelper.deleteChar(charMatcher.getCurrent())
		charMatcher.next()
	}

	return charMatcher.getNumberOfMatch()
}
