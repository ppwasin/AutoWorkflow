plugins {
	id("convention.android.dynamicfeature")
	id("plugin.autoservice")
}
android {
	namespace = "com.boot.dynamicfeature.sample.ondemend"
}
dependencies {
	implementation(projects.app)
	implementation(projects.platform.dynamicfeature)
	implementation(libs.appcompat)
	implementation(libs.playCore)
	implementation(libs.playCoreKtx)
	implementation(libs.bundles.coroutine)
}
