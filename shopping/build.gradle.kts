import org.jetbrains.kotlin.cli.jvm.main

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    id("com.android.library")
    id(infra.plugins.kotlin.multiplatform.get().pluginId)
//    kotlin("multiplatform")
//    alias(infra.plugins.kotlin.multiplatform)
    alias(infra.plugins.kotlin.serialization)
    kotlin("native.cocoapods")
    alias(libs.plugins.ksp)
    id(libs.plugins.sqldelight.get().pluginId)
}

version = "1.0"

kotlin {
    /**########## Target setup ############**/
    /** Backend **/
    jvm()
//    targets.all {
//        compilations.all {
//            kotlinOptions {
//                jvm {
//                    version = Build.java.toString()
//                }
//            }
//        }
//    }
    /** Web **/
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

    /** Mobile **/
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "common"
        }
    }

    /**########## SourceSets setup ############**/
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
                implementation(libs.bundles.koin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android)
            }
        }
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
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)you
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 31
        consumerProguardFiles("lib-proguard-rules.pro") //See also: https://developer.android.com/studio/projects/android-library#Considerations
    }
    compileOptions {
        sourceCompatibility = Build.java
        targetCompatibility = Build.java
    }
}

dependencies {
    add("kspJvm", libs.ksp.koin) //https://kotlinlang.org/docs/ksp-multiplatform.html?section=posts#compilation-and-processing
}

sqldelight {
    database("ShoppingDatabase") {
        packageName = "com.boot.shopping.db"
//        sourceFolders = listOf("sqldelight")
    }
}