package com.boot.playground.ds.unorganize

fun sum(vararg nums: Int): Int {
	return if (nums.isEmpty()) 0
	//    else nums[0] + sum(*nums.drop(1).toIntArray())
	else nums[0] + sum(*nums.sliceArray(1 until nums.size))
}

fun count(vararg nums: Int): Int {
	return if (nums.isEmpty()) 0 else 1 + count(*nums.drop(1).toIntArray())
}

fun sum(nums: Array<Int>, size: Int): Int {
	return if (size <= 0) 0 else nums[size - 1] + sum(nums, size - 1)
}

fun count(nums: Array<Int>, size: Int): Int {
	return if (size <= 0) 0 else 1 + count(nums, size - 1)
}

fun max(nums: Array<Int>, size: Int): Int {
	if (size <= 1) return nums[0]

	val maxValue = max(nums, size - 1)
	return if (nums[size - 1] > maxValue) nums[size - 1] else maxValue
}

fun binaryRecursion(
	sortedValues: Array<Int>,
	max: Int = sortedValues.size - 1,
	min: Int = 0,
	target: Int
): Int? {
	val guessMiddle = (max + min) / 2
	val value = sortedValues[guessMiddle]

	return when {
		value == target -> value
		target < value ->
			binaryRecursion(sortedValues, max = guessMiddle, min = min, target)
		target > value ->
			binaryRecursion(sortedValues, max = max, min = guessMiddle, target)
		else -> null
	}
}

fun main() {
	println(sum(1, 2, 3) == 6)
	println(sum(arrayOf(1, 2, 3), 3) == 6)

	println(count(1, 2, 3) == 3)
	println(count(arrayOf(1, 2, 3), 3) == 3)

	println(max(arrayOf(1, 777, 10, 100, 99, 5), 6) == 777)
	println(binaryRecursion(arrayOf(1, 2, 3, 4, 99, 200), target = 99) == 99)
}
