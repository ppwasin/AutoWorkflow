package com.boot.playground.uri

import java.util.regex.Pattern

class AgodaLinkParser(private val agodaLinkRegex: String) {
	private val pattern = Pattern.compile(agodaLinkRegex)

	fun parseLink(link: String): Triple<String, String, String>? {
		val matcher = pattern.matcher(link)
		return if (matcher.matches()) {
			Triple(matcher.group(1), matcher.group(2), matcher.group(3))
		} else {
			null
		}
	}
}
