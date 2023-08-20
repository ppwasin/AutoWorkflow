plugins {
	id("convention.android.lib")
	id("plugin.junit")
	id("plugin.autoservice")
}
android {
	namespace = "com.boot.dynamicfeature"
}

dependencies {
	implementation(libs.appcompat)
	implementation(libs.playCore)
	implementation(libs.playCoreKtx)
	implementation(libs.bundles.coroutine)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
