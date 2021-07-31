package com.boot.playground.algorithm

class CharMatcher(private val word: String) {
  private var runningIndex: Int = 0
  private val balloonIndex
    get() = runningIndex % word.length
  fun matchNext(character: Char): Boolean {
    return (character == word[balloonIndex]).also { isMatch ->
      println(
        "runningIndex % word.length = balloonIndex -> $runningIndex % ${word.length} = $balloonIndex"
      )
      println("character($character) == word(${word[balloonIndex]})")

      if (isMatch) runningIndex += 1
    }
  }
  fun getNumberOfMatch(): Int = runningIndex / word.length

  fun getCurrent(): Char = word[balloonIndex]
  fun next() {
    runningIndex += 1
  }
}
