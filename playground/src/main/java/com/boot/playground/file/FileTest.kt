package com.boot.playground.file

import java.io.File

class FileTest(val fullPath: String?, val fileName: String) {

  fun writeFile(text: String) {
    try {
      val file = if(fullPath != null) {
        File(fullPath).mkdirs()
        File(fullPath, fileName)
      }else File(fileName)

      file.writeText(text)
      file.createNewFile()
      println("Write success: ${file.absolutePath}")

    } catch (ex: Exception) {
      println("Write error: $ex")
    }
  }

  fun readFile(): String? {
    return try {
      val file = if(fullPath != null) {
        File(fullPath).mkdirs()
        File(fullPath, fileName)
      }else File(fileName)

      val value = file.bufferedReader().use { it.readLine() }
      value
    } catch (ex: Exception) {
      ex.toString()
    }
  }
}
