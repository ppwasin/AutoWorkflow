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

fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
  val maxWindowSize = 2
  var start = 0
  var end = 0

  var maxLength = 0
  val windows = hashMapOf<Char, Int>()
  while (end < s.length) {
    val endChar = s[end]
    windows[endChar] = (windows[endChar] ?: 0) + 1
    ++end

    while (windows.size > maxWindowSize) {
      val startChar = s[start]
      windows[startChar] = (windows[startChar]!!) - 1
      ++start
      if (windows[startChar] == 0) {
        windows.remove(startChar)
      }
    }

    maxLength = maxOf(maxLength, end - start)
  }

  return maxLength
}

fun main() {
  //  println(lengthOfLongestSubstring("dvdf"))
  //  println(lengthOfLongestSubstringTwoDistinct("eceba"))
  //  println(lengthOfLongestSubstringTwoDistinct("ccaabbb"))
  println(isPalindrome(10))
  println(isPalindrome(121))
  println(isPalindrome(-121))
}

fun isPalindrome(x: Int): Boolean {
  val s = x.toString()
  var start = 0
  var end = s.lastIndex
  while (start <= end) {
    if (s[start] != s[end]) return false
    start++
    end--
  }

  return true
}
