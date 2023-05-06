import com.convention.extensions.setupSdk

@Suppress("UnstableApiUsage")
plugins {
	id("com.android.library")
	//    id(infra.plugins.kotlin.multiplatform.get().pluginId)
	kotlin("multiplatform")
	//    alias(infra.plugins.kotlin.multiplatform)
	alias(infra.plugins.kotlin.serialization)
	kotlin("native.cocoapods")
	alias(libs.plugins.ksp)
	id(libs.plugins.sqldelight.get().pluginId)
//  id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
}

version = "1.0"

group = "com.boot"

kotlin {
	/** ########## Target setup ############ */
	jvm {
		compilations.all { kotlinOptions.jvmTarget = versions.java.get() }
		//        withJava()
		testRuns["test"].executionTask.configure { useJUnitPlatform() }
	}
	//    jvm()
	//    targets.all {
	//        compilations.all {
	//            kotlinOptions {
	//                jvm {
	//                    version = Build.java.toString()
	//                }
	//            }
	//        }
	//    }
	/** Web */
	//    js(IR) {
	//        browser {
	//            testTask {
	//                testLogging.showStandardStreams = true
	//                useKarma {
	//                    useChromeHeadless()
	//                    useFirefox()
	//                }
	//            }
	//        }
	//        binaries.executable()
	//    }

	/** Mobile */
	android()
	val isIOSEnable = providers.gradleProperty("include_ios").get().toBoolean()
	if (isIOSEnable) {
		iosX64()
		iosArm64()
		iosSimulatorArm64()
	}

	cocoapods {
		summary = "Some description for the Shared Module"
		homepage = "Link to the Shared Module homepage"
		ios.deploymentTarget = "14.1"
		framework { baseName = "shopping" }
	}

	/** ########## SourceSets setup ############ */
	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(libs.kotlin.serialization)
				implementation(libs.ktor.client.serialization)
				implementation(libs.ktor.client.core)
				implementation(libs.ktor.client.cio)
				implementation(libs.ktor.client.log)
				implementation(libs.coroutine.core)
				implementation(libs.arrowkt.core)
				implementation(libs.klock.common)
			}
		}
		val commonTest by getting { dependencies { implementation(kotlin("test")) } }
		val androidMain by getting { dependencies { implementation(libs.sqldelight.android) } }
		val androidTest by getting {
			dependencies {
				implementation(kotlin("test-junit"))
				implementation("junit:junit:4.13.2")
			}
		}
		val jvmMain by getting {
			dependencies {
				implementation(libs.ktor.serialization)
				implementation(libs.ktor.server.core)
				implementation(libs.ktor.server.netty)
				implementation(libs.logback)
				implementation(libs.kmongo)
				implementation(libs.sqldelight.jvm)
			}
		}
		val jvmTest by getting {
			dependencies {
				implementation(libs.ktor.client.mock)
				implementation(libs.test.kotest.junit5)
				implementation(libs.test.kotest.assert)
				implementation(libs.test.coroutine)
			}
		}

		if (isIOSEnable) {
			val iosX64Main by getting
			val iosArm64Main by getting
			val iosSimulatorArm64Main by getting
			val iosMain by creating {
				dependsOn(commonMain)
				iosX64Main.dependsOn(this)
				iosArm64Main.dependsOn(this)
				iosSimulatorArm64Main.dependsOn(this)
			}
			val iosX64Test by getting
			val iosArm64Test by getting
			val iosSimulatorArm64Test by getting
			val iosTest by creating {
				dependsOn(commonTest)
				iosX64Test.dependsOn(this)
				iosArm64Test.dependsOn(this)
				iosSimulatorArm64Test.dependsOn(this)
			}
		}

		/**
		 * Workaround unused sourceset which generate by AGP
		 * https://issuetracker.google.com/issues/152187160
		 * */
		sourceSets.apply {
			remove(getByName("androidAndroidTestRelease"))
			remove(getByName("androidTestFixtures"))
			remove(getByName("androidTestFixturesDebug"))
			remove(getByName("androidTestFixturesRelease"))
		}
	}
}

android {
	setupSdk()
	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
	//    add("kspJvm", infra.ksp.koin)
	// //https://kotlinlang.org/docs/ksp-multiplatform.html?section=posts#compilation-and-processing
	//    implementation(project(":backend:contract"))
}

sqldelight {
	database("ShoppingDatabase") {
		packageName = "com.boot.shopping.db"
		//        sourceFolders = listOf("sqldelight")
	}
}

//multiplatformSwiftPackage {
//  packageName("KmpBoot")
//  swiftToolsVersion("5.3")
//  targetPlatforms {
//    iOS { v("14") }
//  }
//}
