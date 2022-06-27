package utils.hook

import java.io.File

fun getOrCreateHookDir(rootDirPath: String): String {
    val gitHooksDirectory = File("${rootDirPath}/.git/hooks/")
    if (!gitHooksDirectory.exists()) {
        gitHooksDirectory.mkdirs()
    }
    return "${rootDirPath}/.git/hooks/"
}