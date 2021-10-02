package com.boot.playground.algorithm

fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
  var aIndex = 0
  var sIndex = 0
  while (aIndex <= array.lastIndex || sIndex <= sequence.lastIndex) {
    if (array[aIndex] == sequence[sIndex]) sIndex++
    aIndex++
  }
  return sIndex == sequence.lastIndex
}

fun isValidSubsequence2(array: List<Int>, sequence: List<Int>): Boolean {
  var sIndex = 0
  for (a in array) {
    if (a == sequence[sIndex]) sIndex++
    if (sIndex == sequence.size) return true
  }
  return false
}
