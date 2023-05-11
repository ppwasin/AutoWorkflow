package com.boot.playground.uri

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AgodaLinkParserTest {
	// ^agoda4:\\/\\/([^\\/]+)\\/(.*)\\/([^\\/\\?]+)(\\?.*)?$
	private val parser = AgodaLinkParser("^agoda4:\\/\\/([^\\/]+)\\/(.*)\\/([^\\/\\?]+)(\\?.*)?$")

	@Test
	fun `parse normal link`() {
		val link = "agoda4://hotel/name/7028343?room=1&name=me"
		val expected = Triple("hotel", "name", "7028343")
		val result = parser.parseLink(link)
		assertEquals(expected, result)
	}

	@Test
	fun `parse special link`() {
		val link = "agoda4://hotel/Villa Estrella Beach Resort /#6[rm 4]/7028343?room=1&name=me"
		val expected = Triple("hotel", "Villa Estrella Beach Resort /#6[rm 4]", "7028343")
		val result = parser.parseLink(link)
		assertEquals(expected, result)
	}

	@Test
	fun `parse special link without query parameters`() {
		val link = "agoda4://hotel/Villa Estrella Beach Resort /#6[rm 4]/7028343"
		val expected = Triple("hotel", "Villa Estrella Beach Resort /#6[rm 4]", "7028343")
		val result = parser.parseLink(link)
		assertEquals(expected, result)
	}

	@Test
	fun `parse non hotel link`() {
		val link = "https://www.google.com/"
		val result = parser.parseLink(link)
		assertNull(result)
	}
}
