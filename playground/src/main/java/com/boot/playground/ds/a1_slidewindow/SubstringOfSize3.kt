package com.boot.playground.ds.a1_slidewindow

import kotlin.math.max

fun countGoodSubstrings(s: String): Int {
  var count = 0
  for (i in 0..s.length - 3) {

    val substr = s.substring(i, i + 3)
    if (substr.toSet().size == 3) {
      count++
    }
  }

  return count
}

fun lengthOfLongestSubstring(s: String): Int {
  var start = 0
  var end = 0
  var longest = 0
  val window = hashSetOf<Char>()
  while (end < s.length) {
    if (!window.contains(s[end])) {
      window.add(s[end])
      end++
      longest = max(longest, end - start)
    } else {
      window.remove(s[start])
      start++
    }
  }

  return longest
}

fun main() {
  println(lengthOfLongestSubstring("dvdf"))
}
