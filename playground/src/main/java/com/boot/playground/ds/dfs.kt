package com.boot.playground.ds

import java.util.Stack

//fun dfsWithoutRecursion(adjVertices: Array<Int>, start: Int) {
//    val stack: Stack<Int> = Stack<Int>()
//    val isVisited = BooleanArray(adjVertices.size)
//    stack.push(start)
//    while (!stack.isEmpty()) {
//        val current: Int = stack.pop()
//        isVisited[current] = true
//        visit(current)
//        for (dest in adjVertices[current]) {
//            if (!isVisited[dest]) stack.push(dest)
//        }
//    }
//}