package com.boot.playground.algorithm.unorganize

fun twoNumberSum(array: MutableList<Int>, targetSum: Int): List<Int> {
	val map =
		array.foldIndexed(hashMapOf<Int, Int>()) { index, acc, item ->
			acc.also { it[item] = index }
		}

	var current: Int
	array.forEachIndexed { index, item ->
		current = item
		val mapTarget = targetSum - current
		if (map.containsKey(mapTarget) && map[mapTarget] != index) {
			return listOf(current, mapTarget)
		}
	}
	return emptyList()
}

fun twoNumberSum3(array: MutableList<Int>, targetSum: Int): List<Int> {
	array.sort()
	var left = 0
	var right = array.lastIndex
	while (left < right) {
		val leftValue = array[left]
		val rightValue = array[right]
		val currentSum = leftValue + rightValue
		when {
			currentSum == targetSum -> return listOf(leftValue, rightValue)
			currentSum < targetSum -> left += 1
			currentSum > targetSum -> right -= 1
		}
	}
	return emptyList()
}
