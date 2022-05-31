apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.modular.composelib")
    alias(libs.plugins.ksp)
}

dependencies {

    compileOnly(libs.ksp.processing)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(libs.paging.room)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
}
