package com.boot.components.utils

fun <A, B, C> List<A>.combineLatest(
  other: List<B>,
  merge: (A, B) -> C
): List<C> {
  return flatMap { a -> other.map { b -> merge(a, b) } }
}

fun <A, B, C, Output> List<A>.combineLatest(
  other1: List<B>,
  other2: List<C>,
  merge: (A, B, C) -> Output
): List<Output> {
  return flatMap { a ->
    other1.flatMap { b -> other2.map { c -> merge(a, b, c) } }
  }
}
