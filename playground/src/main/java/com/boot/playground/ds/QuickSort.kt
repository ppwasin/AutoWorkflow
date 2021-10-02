package com.boot.playground.ds

/*
https://developerlife.com/2018/08/16/algorithms-in-kotlin-1/#quick-sort
This algorithm has really low memory footprint,
since the items are swapped in place in the same array, unlike merge sort, which can take up more memory.
 */

// WorstCase: O(N^2) but average at O(n log n).
// Normal sort always O(N^2)
// merge sort is O(n log n)

// O(n+n+nlogn) = O(NlogN)
fun quickSortRecursive(nums: List<Int>): List<Int> {
  if (nums.size <= 1) return nums
  val pivot = nums[0]
  val less = nums.filter { it < pivot } // n
  val greater = nums.filter { it > pivot } // n
  return quickSortRecursive(less) + pivot + quickSortRecursive(greater)
}

// Worstcase: O(n^2)
// Bestcase: O(n log n)
fun quickSortBestEasyToUnderstand(nums: List<Int>): List<Int> =
  when {
    nums.size < 2 -> nums
    else -> {
      val pivot = nums.first()
      val (smaller, greater) = nums.subList(1, nums.size).partition { it <= pivot }
      quickSortBestEasyToUnderstand(smaller) + pivot + quickSortBestEasyToUnderstand(greater)
    }
  }

// Worstcase: O(n^2)
// Bestcase: O(n log n)
fun quickSort(nums: IntArray, startIndex: Int = 0, endIndex: Int = nums.size - 1) {
  if (startIndex < endIndex) {
    val pivotIndex = partition(nums, startIndex, endIndex) // n
    quickSort(nums, startIndex, pivotIndex - 1) // Before pivot index
    quickSort(nums, pivotIndex + 1, endIndex) // After pivot index
  }
}
// 7, 8, 1, 0, 100, 9, 6
fun partition(nums: IntArray, startIndex: Int, endIndex: Int): Int {
  // Element to be placed at the correct position in the list
  val pivotValue = nums[endIndex] // 6

  // Index of element smaller than pivotValue
  var smallerElementIndex = startIndex // 0

  // Make a single pass through the list (not including endIndex)
  for (index in startIndex until endIndex) {
    // If current element is smaller than equal to pivotValue then swap it w/
    // the element at smallerElementIndex
    val valueAtIndex = nums[index] // [0]:7
    if (valueAtIndex < pivotValue) { // 7<6, 8<6, 1<6
      nums.swap(smallerElementIndex, index) // swap between 0, 2: 7,8,1,... -> 1,8,7,...
      smallerElementIndex++ // 0 -> 1
    }
  }

  // Finally move the pivotValue into the right place on the nums
  nums.swap(smallerElementIndex, endIndex)

  // Return the index just after where the pivot value ended up //0,1, 6, 7,8,100,9
  return smallerElementIndex
}

fun IntArray.swap(i: Int, j: Int) {
  val tmp = get(i)
  set(i, get(j))
  set(j, tmp)
}

fun main() {
  println(quickSortRecursive(listOf(7, 8, 1, 0, 100, 9, 6)) == listOf(0, 1, 6, 7, 8, 9, 100))
  println(
    quickSortBestEasyToUnderstand(listOf(7, 8, 1, 0, 100, 9, 6)) == listOf(0, 1, 6, 7, 8, 9, 100)
  )

  val nums = intArrayOf(7, 8, 1, 0, 100, 9, 6)
  quickSort(nums)
  println(nums.map { it })
}
