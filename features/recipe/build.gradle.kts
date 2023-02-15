@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("com.convention.composeandroidlib")
	alias(libs.plugins.ksp)
	id("plugin.junit")
	id("plugin.spotless")
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
