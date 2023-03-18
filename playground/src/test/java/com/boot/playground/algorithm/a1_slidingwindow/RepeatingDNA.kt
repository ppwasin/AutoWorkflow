package com.boot.playground.algorithm.a1_slidingwindow

import org.junit.jupiter.api.Test

fun findRepeatedDNASequences(str: String): List<String> {
	val seen = hashSetOf<String>()
	val repeated = hashSetOf<String>()
	for (i in 0..str.length - 10) {
		val subStr = str.substring(i, i + 10)
		if (!seen.add(subStr)) repeated.add(subStr)
	}
	return repeated.toList()
}

class RepeatingDNA {
	@Test
	fun `test1`() {
		val result = findRepeatedDNASequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT")
		println(result)
		assert(result == listOf("AAAAACCCCC", "CCCCCAAAAA"))
	}
}
