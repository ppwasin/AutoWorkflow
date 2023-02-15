plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
}
repositories {
	google()
	gradlePluginPortal()
	mavenCentral()
}

gradlePlugin {
	plugins {
		create("junit5-plugin") {
			id = "plugin.junit"
			implementationClass = "JunitPlugin"
		}
		create("spotless-plugin") {
			id = "plugin.spotless"
			implementationClass = "SpotlessPlugin"
		}
		create("dagger-plugin") {
			id = "plugin.dagger"
			implementationClass = "DaggerPlugin"
		}
	}
}
dependencies {
	implementation(infra.kotlinGradle)
	implementation(infra.androidGradle)
	implementation(infra.spotless)
}
