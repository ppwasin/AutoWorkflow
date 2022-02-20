package com.boot.common

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}