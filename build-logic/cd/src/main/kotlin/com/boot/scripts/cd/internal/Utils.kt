package com.boot.scripts.cd.internal

fun <A, B> retry(f: (A) -> B, times: Int, delay: Long = 10) = { a: A ->
	(0..times).fold("Not executed") { _, n ->
		try {
			println("Try: $n:")
			return@fold "Success $n: ${f(a)}"
		} catch (e: Exception) {
			Thread.sleep(delay)
			"${e.message}"
		}
	}
}
