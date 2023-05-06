plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
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
	implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
	implementation(files(infra.javaClass.superclass.protectionDomain.codeSource.location))
	implementation(infra.kotlinGradle)
	implementation(infra.androidGradle)
	implementation(infra.spotless)
}
