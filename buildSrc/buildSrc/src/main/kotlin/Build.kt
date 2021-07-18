import org.gradle.api.JavaVersion

object Build {
	const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
	const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	val java = JavaVersion.VERSION_11
	const val compileSdk = 30
	const val minSdk = 23
	const val targetSdk = 30
	const val appId = "com.boot.autoworkflow"
	
	object GoogleService {
		const val classpath = "com.google.gms:google-services:${Versions.googleService}"
		const val pluginId = "com.google.gms.google-services"
		const val firebasePlatform = "com.google.firebase:firebase-bom:28.2.1"
		const val firebaseAnalytic = "com.google.firebase:firebase-analytics-ktx"
	}
	object FirebaseAppDistribution {
		const val classpath = "com.google.firebase:firebase-appdistribution-gradle:2.1.3"
		const val pluginId = "com.google.firebase.appdistribution"
	}
	
	object Spotless {
		const val classpath = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"
		const val pluginId = "com.diffplug.spotless"
	}
	
	object PlayPublisher {
		const val classpath = "com.github.triplet.gradle:play-publisher:${Versions.playPublisher}"
		const val pluginId = "com.github.triplet.play"
	}
}