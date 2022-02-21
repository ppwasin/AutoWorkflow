dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
        create("infra") {
            from(files("../gradle/infra.versions.toml"))
        }
    }
}