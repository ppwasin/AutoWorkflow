package com.boot.playground.algorithm.a1_slidingwindow

import kotlin.math.min
import org.junit.jupiter.api.Test

/** https://leetcode.com/problems/minimum-size-subarray-sum/description/ */

// O(n)
fun minSubArrayLen(target: Int, nums: IntArray): Int {
  var minLength = Int.MAX_VALUE
  var start = 0
  var end = 0
  var sum = 0
  while (end < nums.size) {
    sum += nums[end]
    end++

    while (sum >= target) {
      minLength = min(minLength, end - start)
      sum -= nums[start]
      start++
    }
  }
  return if (minLength == Int.MAX_VALUE) 0 else minLength
}

// O(n log n)
fun minSubArrayLen2(target: Int, nums: IntArray): Int {
  var minLength = Int.MAX_VALUE
  var start = 0
  var end = 0
  var sum = 0
  while (end < nums.size) {
    sum += nums[end]
    end++

    while (sum >= target) {
      minLength = min(minLength, end - start)
      sum -= nums[start]
      start++
    }
  }
  return if (minLength == Int.MAX_VALUE) 0 else minLength
}

class `Minimum Size Subarray Sum` {
  @Test
  fun `test1`() {
    val result = minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3))
    assert(result == 2) // 4,3
  }
}
