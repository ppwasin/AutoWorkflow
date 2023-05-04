import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("com.android.library")
	kotlin("multiplatform")
	alias(infra.plugins.kotlin.serialization)
	alias(ziplineLib.plugins.zipline.plugin)
}

kotlin {
	iosArm64()
	iosX64()
	iosSimulatorArm64()

	android()

	js(IR) {
		browser()
		binaries.executable()
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				api(ziplineLib.zipline.core)
			}
		}
		val hostMain by creating {
			dependsOn(commonMain)
			dependencies {
				implementation(ziplineLib.zipline.loader)
				api(ziplineLib.okio.core)
			}
		}

		val androidMain by getting {
			dependsOn(hostMain)
			dependencies {
				implementation(ziplineLib.okHttp.core)
			}
		}
		val iosMain by creating {
			dependsOn(hostMain)
		}
		targets.withType<KotlinNativeTarget> {
			val main by compilations.getting
			main.defaultSourceSet.dependsOn(iosMain)
		}
	}
}

android {
	val versions = infra.versions
	compileSdk = infra.versions.compileSdk.get().toInt()
	namespace = "com.boot.play.zipline.presenters"
	compileOptions {
		sourceCompatibility = JavaVersion.toVersion(versions.java.get())
		targetCompatibility = JavaVersion.toVersion(versions.java.get())
	}
}

zipline {
	mainFunction.set("app.cash.zipline.samples.worldclock.main")
}
