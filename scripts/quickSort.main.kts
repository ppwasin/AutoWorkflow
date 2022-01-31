#!/usr/bin/env kotlin
/**
 * base-case: empty array, array with one element
 * */
fun quickSort(input: List<Int>): List<Int>{
	val pivot = input.first()
	val lessSubArray = input
	println(input)
	return emptyList()
}

quickSort(listOf(33, 15, 10))