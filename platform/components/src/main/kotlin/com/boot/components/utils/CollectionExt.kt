package com.boot.components.utils

fun <A, B, C> Collection<A>.combineLatest(other: Collection<B>, merge: (A, B) -> C): Collection<C> {
  return flatMap { a -> other.map { b -> merge(a, b) } }
}

fun <A, B, C> List<A>.combineLatest(other: List<B>, merge: (A, B) -> C): List<C> {
  return flatMap { a -> other.map { b -> merge(a, b) } }
}
