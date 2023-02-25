plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
//  alias(infra.plugins.buildconfig)
}

gradlePlugin {
	plugins {
		create("android-lib-module") {
			id = "com.convention.android"
			implementationClass = "com.convention.AndroidPlugin"
		}
		create("compose-lib-module") {
			id = "com.convention.android-compose"
			implementationClass = "com.convention.AndroidComposePlugin"
		}
	}
}

dependencies {
//	implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
//	implementation(files(infra.javaClass.superclass.protectionDomain.codeSource.location))
	implementation(infra.kotlinGradle)
	implementation(infra.androidGradle)
}

//buildConfig {
//  buildConfigField("String", "COMPILE_SDK", "\"${project.name}\"")
//  buildConfigField("String", "APP_VERSION", provider { "\"${project.version}\"" })
//  buildConfigField("String", "APP_SECRET", "\"Z3JhZGxlLWphdmEtYnVpbGRjb25maWctcGx1Z2lu\"")
//  buildConfigField("long", "BUILD_TIME", "${System.currentTimeMillis()}L")
//  buildConfigField("boolean", "FEATURE_ENABLED", "${true}")
//  buildConfigField("IntArray", "MAGIC_NUMBERS", "intArrayOf(1, 2, 3, 4)")
//  buildConfigField("com.github.gmazzo.SomeData", "MY_DATA", "new SomeData(\"a\",1)")
//}
