import com.convention.addComposeDependencies

plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	id("plugin.junit")
}
android {
	namespace = "com.boot.payment"
}
dependencies {
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	addComposeDependencies(project)
	implementation(libs.billing)
	implementation(libs.bundles.coroutine)
	implementation(libs.paging.compose)
	implementation(libs.bundles.arrowkt)


	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
