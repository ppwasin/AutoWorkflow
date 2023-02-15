package com.boot.autoworkflow

fun hasJunit() =
  try {
    Class.forName("org.junit.Test")
    true
  } catch (allGood: Throwable) {
    false
  }
