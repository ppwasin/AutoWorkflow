package com.boot.scripts.cd.internal

import com.lordcodes.turtle.GitCommands

fun GitCommands.gitCommand(cmds: String) = gitCommand(cmds.split(" "))
