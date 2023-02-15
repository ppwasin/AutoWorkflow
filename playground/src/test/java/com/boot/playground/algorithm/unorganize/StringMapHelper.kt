package com.boot.playground.algorithm.unorganize

class StringMapHelper(string: String) {
  private val map = mutableMapOf<Char, Int>()

  init {
    for (char in string) {
      val count = map[char] ?: 0
      map[char] = count + 1
    }
  }

  fun contain(char: Char): Boolean {
    println("contain($char): ${map[char]}")
    return map[char] != null
  }

  fun deleteChar(char: Char) {
    val count = map[char] ?: 0
    if (count - 1 <= 0) map.remove(char) else map[char] = count - 1
  }
}
