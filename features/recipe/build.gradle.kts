plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	alias(infra.plugins.ksp)
	id("plugin.junit")
}
android {
	namespace = "com.boot.recipe"
	defaultConfig {
		ksp {
			arg("room.schemaLocation", "$projectDir/schemas")
		}
	}
}
dependencies {
	compileOnly(infra.ksp.processing)
	implementation(libs.paging.compose)
	implementation(libs.paging.runtime)
	implementation(libs.paging.room)

	implementation(libs.room.runtime)
	ksp(libs.room.compiler)
	implementation(libs.room.ktx)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
