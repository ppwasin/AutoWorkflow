import org.gradle.api.JavaVersion

object Build {
	val java = JavaVersion.VERSION_11
	const val compileSdk = 31
	const val minSdk = 23
	const val targetSdk = 31
	const val appId = "com.boot.autoworkflow"
	
	object GoogleService {
		private const val version = "4.3.8"
		const val classpath = "com.google.gms:google-services:$version"
		const val pluginId = "com.google.gms.google-services"
//		const val firebasePlatform = "com.google.firebase:firebase-bom:28.2.1"
	}
	object FirebaseAppDistribution {
		const val classpath = "com.google.firebase:firebase-appdistribution-gradle:2.1.3"
		const val pluginId = "com.google.firebase.appdistribution"
	}
	
	object PlayPublisher {
		private const val version = "3.6.0"
		const val classpath = "com.github.triplet.gradle:play-publisher:$version"
		const val pluginId = "com.github.triplet.play"
	}
}