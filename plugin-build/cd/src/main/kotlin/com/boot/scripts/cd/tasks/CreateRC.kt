package com.boot.scripts.cd.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CreateRC: DefaultTask() {
	@TaskAction
	fun setup() {
		println("Hello From CreateRC")
	}
}