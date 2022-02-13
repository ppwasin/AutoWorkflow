dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
        create("build") {
            from(files("../gradle/build.versions.toml"))
        }
    }
}