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
		create("room-plugin") {
			id = "plugin.room"
			implementationClass = "RoomPlugin"
		}
		create("showkase-plugin") {
			id = "plugin.showkase"
			implementationClass = "ShowkasePlugin"
		}
		create("autoservice-plugin") {
			id = "plugin.autoservice"
			implementationClass = "AutoServicePlugin"
		}
	}
}
dependencies {
	implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
	implementation(files(infra.javaClass.superclass.protectionDomain.codeSource.location))
	implementation(infra.kspGradle)
	implementation(infra.kotlinGradle)
	implementation(infra.androidGradle)
	implementation(infra.spotless)
}
