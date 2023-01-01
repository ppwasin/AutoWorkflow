package com.boot.playground.ds

fun mergeSort(list: MutableList<String>): MutableList<String> {
  // Can't split lists anymore, so stop recursion
  val length = list.size
  if (length <= 1) return list

  // Split the list into two and recurse (divide)
  val middleIndex = length / 2
  val leftList = mergeSort(list.subList(0, middleIndex))
  val rightList = mergeSort(list.subList(middleIndex, length))

  // Merge the left and right lists (conquer)
  return merge(leftList, rightList)
}

/**
 * In this step, the actual sorting of 2 already sorted lists occurs.
 *
 * The merge sort algorithm takes advantage of the fact that two sorted lists
 * can be merged into one sorted list very quickly.
 */
fun merge(
  leftList: MutableList<String>,
  rightList: MutableList<String>
): MutableList<String> {
  val result = mutableListOf<String>()
  var leftIndex = 0
  var rightIndex = 0

  while (leftIndex < leftList.size && rightIndex < rightList.size) {
    val lhs = leftList[leftIndex]
    val rhs = rightList[rightIndex]
    if (lhs < rhs) {
      result.add(lhs)
      leftIndex++
    } else {
      result.add(rhs)
      rightIndex++
    }
  }

  // Copy remaining elements of leftList (if any) into the result
  while (leftIndex < leftList.size) {
    result.add(leftList[leftIndex])
    leftIndex++
  }

  // Copy remaining elements of rightList (if any) into the result
  while (rightIndex < rightList.size) {
    result.add(rightList[rightIndex])
    rightIndex++
  }

  return result
}
