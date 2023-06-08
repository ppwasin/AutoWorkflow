plugins {
	id("convention.android.lib")
	id("org.jetbrains.kotlin.android")
	id("plugin.junit")
}
android {
	namespace = "com.boot.dynamicfeature"
}

dependencies {
	implementation(libs.appcompat)
	implementation(libs.playCore)
	implementation(libs.playCoreKtx)
	compileOnly(libs.googleServiceAutoAnnotations)
	implementation(libs.googleServiceAuto)

	implementation(libs.bundles.coroutine)
}
