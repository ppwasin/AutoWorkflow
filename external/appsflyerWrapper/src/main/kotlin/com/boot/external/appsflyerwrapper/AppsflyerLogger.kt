package com.boot.external.appsflyerwrapper

interface AppsflyerLogger {
  fun debug(message: String)
  fun verbose(message: String)

  companion object {
    operator fun invoke(): AppsflyerLogger {
      return object : AppsflyerLogger {
        override fun debug(message: String) {
          println("[Appsflyer] $message")
        }

        override fun verbose(message: String) {
          println("[Appsflyer] $message")
        }
      }
    }
  }
}
