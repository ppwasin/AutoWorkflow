package com.boot.playground.algorithm.easy

import kotlin.math.abs

fun sortedSquaredArray(array: List<Int>): List<Int> {
	val result = array.map { 0 }.toMutableList()
	var leftIndex = 0
	var rightIndex = array.lastIndex

	for (index in array.lastIndex downTo 0) {
		val left = array[leftIndex]
		val right = array[rightIndex]
		if (abs(left) > abs(right)) {
			result[index] = left
			leftIndex++
		} else {
			result[index] = right
			rightIndex++
		}
	}
	return result
}
