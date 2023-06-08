import com.convention.addComposeDependencies

plugins {
	id("convention.android.lib")
	id("convention.android.compose")
	id("plugin.junit")
	id("kotlin-parcelize")
	id("plugin.showkase")
}
android {
	namespace = "com.boot.entrypoint"
}
dependencies {
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	implementation(projects.features.payment)
	implementation(projects.fake.pagination)
	addComposeDependencies(project)

	implementation(libs.bundles.coroutine)
	implementation(libs.paging.compose)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
