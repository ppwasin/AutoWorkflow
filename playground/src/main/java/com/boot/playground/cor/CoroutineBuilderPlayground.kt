package com.boot.playground.cor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun playCoroutineScope() {
    println("0: ${getThreadName()}")
    runBlocking {
        println("Inside runBlocking: ${getThreadName()}")
        withContext(Dispatchers.Default) {
            println("withContext(Dispatchers.Default): ${getThreadName()}")
            coroutineScope {
                println("coroutineScope{...}: ${getThreadName()}")
            }
        }
    }
}

private fun getThreadName() = Thread.currentThread().name

fun main() {
    playCoroutineScope()
}