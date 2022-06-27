#!/usr/bin/env kotlin
@file:Repository("https://repo.maven.apache.org/maven2/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
@file:DependsOn("com.github.pgreze:kotlin-process:1.3.1") //https://github.com/pgreze/kotlin-process
@file:Suppress("OPT_IN_USAGE")

import com.github.pgreze.process.process
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.Charset

//var i = 0
//shell(" git diff origin/main --stat -- '**snapshots/**/*.png'").bufferedReader().useLines {
//    println("${i++}: $it")
//}
runBlocking {
    val res = process("git diff origin/main --stat -- '**snapshots/**/*.png'")
    println(res.output)
}


fun shell(command: String): InputStream {
    println("$ $command")

    // "/bin/bash"
    val process = ProcessBuilder("sh", "-c", command).start()

    val exitCode = process.waitFor()
    if (exitCode != 0) {
        val platformMessages =
                """
        ${String(BufferedInputStream(process.inputStream).readAllBytes(), Charset.defaultCharset())}
        ${String(BufferedInputStream(process.errorStream).readAllBytes(), Charset.defaultCharset())}
        """
                        .trimIndent()
                        .trim()

        throw IllegalStateException(
                "Execution of '$command' failed with exit code $exitCode!\n$platformMessages\n---"
        )
    }

    return process.inputStream
}
