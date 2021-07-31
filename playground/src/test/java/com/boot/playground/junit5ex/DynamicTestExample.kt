package com.boot.playground.junit5ex

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicTestExample {
  @TestFactory
  fun testSquares() =
    listOf(1 to 1, 2 to 4, 3 to 9, 4 to 16, 5 to 25).map { (input, expected) ->
      DynamicTest.dynamicTest("when I calculate $input^2 then I get $expected") {
        Assertions.assertEquals(expected, input * input)
      }
    }
}
