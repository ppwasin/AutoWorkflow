package com.boot.external.appsflyerwrapper.utils

import java.util.*

class CircularFifo<T>(private val sizeLimit: Int) {
	private val items = Stack<T>()
	fun push(item: T) {

		items.push(item)
	}
}
