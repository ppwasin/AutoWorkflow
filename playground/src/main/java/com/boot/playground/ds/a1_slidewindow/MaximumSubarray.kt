package com.boot.playground.ds.a1_slidewindow

import kotlin.math.max

// https://leetcode.com/problems/maximum-subarray/
fun maxSubArray_1(nums: IntArray): Int {
  var highestSum = Int.MIN_VALUE
  val n = nums.size

  for (i in 0 until n) {
    var currentSum = 0
    for (j in i until n) {
      currentSum += nums[j]
      highestSum = max(highestSum, currentSum)
    }
  }
  return highestSum
}

fun maxSubArray_2(nums: IntArray): Int {
  var highestSum = Int.MIN_VALUE
  var currentSum = 0
  for (i in nums.indices) {
    currentSum = max(currentSum + nums[i], nums[i])
    highestSum = max(highestSum, currentSum)
  }
  return highestSum
}

fun main() {
  //  maxSubArray_1(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
  println(maxSubArray_2(intArrayOf(5, 4, -1, 7, 8)))
}
