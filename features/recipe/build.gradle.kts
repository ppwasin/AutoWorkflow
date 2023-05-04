@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	alias(libs.plugins.ksp)
	id("plugin.junit")
}
android {
	namespace = "com.boot.recipe"
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
