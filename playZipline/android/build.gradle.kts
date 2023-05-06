plugins {
	id("com.android.application")
	kotlin("android")
}

android {
	val versions = infra.versions
	namespace = "app.cash.zipline.samples.worldclock"
	compileSdk = versions.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "com.example.zipline.worldclock"
		minSdk = versions.minSdk.get().toInt()
		targetSdk = versions.targetSdk.get().toInt()
	}

	compileOptions {
//		isCoreLibraryDesugaringEnabled = true
		sourceCompatibility = JavaVersion.toVersion(versions.java.get())
		targetCompatibility = JavaVersion.toVersion(versions.java.get())
	}
	kotlinOptions {
		jvmTarget = versions.java.get()
	}

	buildFeatures {
		compose = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = infra.versions.composeCompiler.get()
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
	implementation(platform(libs.compose.bom))
	implementation(libs.bundles.compose)

	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.bundles.coroutine)
	debugImplementation(libs.compose.uiTooling)
//	add(PLUGIN_CLASSPATH_CONFIGURATION_NAME, "app.cash.zipline:zipline-kotlin-plugin")
//	coreLibraryDesugaring(libs.android.desugarJdkLibs)
}
