package com.boot.playground.ds.graphsearch

typealias Graph = MutableMap<String, List<String>>

fun getGraph(): MutableMap<String, List<String>> {
    val graph = mutableMapOf<String, List<String>>()
    graph["you"] = listOf("alice", "bob", "claire")
    graph["bob"] = listOf("anuj", "peggy")
    graph["alice"] = listOf("peggy")
    graph["claire"] = listOf("thom", "jonny")
    graph["anuj"] = emptyList()
    graph["peggy"] = emptyList()
    graph["thom"] = emptyList()
    graph["jonny"] = emptyList()

    return graph
}