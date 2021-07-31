package com.boot.playground.algorithm

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WordSearchTest {

  @ParameterizedTest
  @MethodSource("sourceMethod")
  fun testSolution1(input: String, expectedCount: Int) {
    val count = solution(input)
    assertEquals(expectedCount, count)
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  fun testSolution2(input: String, expectedCount: Int) {
    val count = solution2(input)
    assertEquals(expectedCount, count)
  }

  companion object {
    @JvmStatic
    fun sourceMethod(): Stream<Arguments?>? {
      return Stream.of(
          Arguments.of("BALLOON", 1),
          Arguments.of("LLOONBA", 1),
          Arguments.of("LwAweNdsfOLBO", 1),
          Arguments.of("LwAweLwAweNdsfOLBONdsfOLBOLwAweNdsfOLBO", 3),
      )
    }
  }
}

class WordSearchTest2 {
  private val assertList =
      listOf(
          "BALLOON" to 1,
          "LLOONBA" to 1,
          "LwAweNdsfOLBO" to 1,
          "LwAweLwAweNdsfOLBONdsfOLBOLwAweNdsfOLBO" to 3)
  private val solutionList =
      listOf(
          "solution1" to { s: String -> solution(s) }, "solution2" to { s: String -> solution2(s) })

  private val testScenarios: Collection<TestScenario> =
    assertList.combineLatest(solutionList){ (input, expected), (label, function) ->
      TestScenario(input = input, expected = expected, function = function, label = label)
    }

  @TestFactory
  fun testWordWithFactory() =
    testScenarios.map { (input, expected, function, label) ->
        DynamicTest.dynamicTest("Test $label: $input -> $expected") {
          val count = function(input)
          assertEquals(expected, count)
        }
      }

  data class TestScenario(
      val input: String,
      val expected: Int,
      val function: (String) -> Int,
      val label: String
  )
}

fun <A, B, C> Collection<A>.combineLatest(other: Collection<B>, merge: (A, B) -> C): Collection<C> =
    flatMap { a -> other.map { b -> merge(a, b) }
}
