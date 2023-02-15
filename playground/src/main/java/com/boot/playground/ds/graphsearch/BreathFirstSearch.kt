package com.boot.playground.ds.graphsearch

fun main() {
  val graph = getGraph()
  println(bfsSearch(graph = graph, "you"))
}

fun bfsSearch(graph: Graph, name: String): Boolean {
  //    val searchQueue: Queue<String> = LinkedList(graph[name])
  //    val searchQueue = Stack<String>()
  val searchQueue = ArrayDeque<String>()
  graph[name]?.let(searchQueue::addAll)
  println(searchQueue)
  val searched = mutableListOf<String>()
  while (!searchQueue.isEmpty()) {
    val item = searchQueue.pop()
    println(item)
    if (item != null && !searched.contains(item)) {
      if (item.last() == 'm') {
        print("$item is mango seller")
        return true
      } else {
        graph[item]?.let(searchQueue::addAll)
        searched.add(item)
      }
    }
  }
  return false
}

fun <T> ArrayDeque<T>.pop(): T? {
  val item = firstOrNull()
  removeFirst()
  return item
}
