package com.boot.playground.ds

fun <T> linearSearch(values: Array<T>, isMatch: (T) -> Boolean): T? {
  //    values.find(isMatch)
  for (value in values) if (isMatch(value)) return value
  return null
}

fun <T> binarySearch(
  sortedValues: Array<T>,
  targetIsMatch: (T) -> Boolean,
  targetIsLessThan: (T) -> Boolean,
  targetIsMoreThan: (T) -> Boolean
): T? {
  var max = sortedValues.size - 1
  var min = 0

  while (max >= min) {
    val guessMiddle = (max + min) / 2
    val value = sortedValues[guessMiddle]
    when {
      targetIsMatch(value) -> return value
      targetIsLessThan(value) ->
        // target would be in the left half
        max = (guessMiddle - 1)
      targetIsMoreThan(value) ->
        // target would be in the right half
        min = (guessMiddle + 1)
    }
  }
  return null
}

// fun <T> binarySearchFp(
//    sortedValues: Array<T>,
//    targetIsMatch: (T) -> Boolean,
//    targetIsLessThan: (T) -> Boolean,
//    targetIsMoreThan: (T) -> Boolean
// ): T? {
//    sortedValues.reduce { acc, t ->  }
//
// }

fun intBinarySearch(sortedValues: Array<Int>, target: Int) =
  binarySearch(
    sortedValues = sortedValues,
    targetIsMatch = { target == it },
    targetIsLessThan = { target < it },
    targetIsMoreThan = { target > it }
  )

fun test() {
  val array = arrayListOf<String>()
  val ist = listOf<String>()
}
