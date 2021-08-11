package com.boot.scripts.cd.internal

internal fun shell(
	command: String,
): String {
	println("$ $command")
	
	// "/bin/bash"
	val process = ProcessBuilder("sh", "-c", command).start()
	
	val code = process.waitFor()
	val error = process.errorStream?.bufferedReader()?.use { it.readText() }?.trim()
	val output = process.inputStream?.bufferedReader()?.use { it.readText() }?.trim()
	
	if(!error.isNullOrEmpty())
		throw IllegalStateException("$ $command\nExitCode: $code\n$error")
	
	return output ?: ""
}