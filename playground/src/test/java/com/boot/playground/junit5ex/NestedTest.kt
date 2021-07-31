package com.boot.playground.junit5ex

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class NestedTestWithHierarchicalSetupMethods {

  var state = ""
  @BeforeEach
  fun outerSetup() {
	  state += "outer"
  }

  @Nested
  internal inner class InnerClass {

    @BeforeEach
    fun innerSetup() {
      state = "$state-inner"
    }

    @Test
    fun checkSetup() {
      assertEquals("outer-inner", state)
    }
  }
}
