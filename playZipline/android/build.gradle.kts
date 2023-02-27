import org.jetbrains.kotlin.gradle.plugin.PLUGIN_CLASSPATH_CONFIGURATION_NAME

plugins {
	id("com.android.application")
	kotlin("android")
}

android {
	namespace = "app.cash.zipline.samples.worldclock"
	compileSdk = infra.versions.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "com.example.zipline.worldclock"
		minSdk = infra.versions.minSdk.get().toInt()
		targetSdk = infra.versions.targetSdk.get().toInt()
	}

	compileOptions {
//		isCoreLibraryDesugaringEnabled = true
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}

	buildFeatures {
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.compose.get()
	}

	packagingOptions {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}

//	val samples by signingConfigs.creating {
//		storeFile(file("../../samples.keystore"))
//		storePassword("javascript")
//		keyAlias("javascript")
//		keyPassword("javascript")
//	}

//	buildTypes {
//		val debug by getting {
//			applicationIdSuffix = ".debug"
//			signingConfig = samples
//		}
//		val release by getting {
//			signingConfig = samples
//		}
//	}
}

dependencies {
	implementation(ziplineLib.zipline.core)
	implementation(ziplineLib.zipline.loader)
	implementation(ziplineLib.zipline.profiler)
	implementation(projects.playZipline.presenters)
//	implementation(libs.material)
//	implementation(libs.compose.activity)
//	implementation(libs.androidx.core.ktx)
//	implementation(libs.androidx.lifecycle)
//	implementation(libs.compose.coil)
//	implementation(libs.compose.material)
//	implementation(libs.compose.ui)
	implementation(libs.bundles.compose)
	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.bundles.coroutine)
	debugImplementation(libs.compose.uiTooling)
//	add(PLUGIN_CLASSPATH_CONFIGURATION_NAME, "app.cash.zipline:zipline-kotlin-plugin")
//	coreLibraryDesugaring(libs.android.desugarJdkLibs)
}
