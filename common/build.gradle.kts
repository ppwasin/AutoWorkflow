@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id(infra.plugins.kotlin.multiplatform.get().pluginId)
//    kotlin("multiplatform")
//    alias(infra.plugins.kotlin.multiplatform)
    alias(infra.plugins.kotlin.serialization)
    kotlin("native.cocoapods")
}

version = "1.0"

kotlin {
    /**########## Target setup ############**/
    /** Backend **/
    jvm()
//    jvm {
//        compilations.all {
//            kotlinOptions.jvmTarget = Build.java.toString()
//        }
//        withJava()
//    }
    targets.all {
        compilations.all {
            kotlinOptions {
                jvm {
                    version = Build.java.toString()
                }
            }
        }
    }
    /** Web **/
//    js {
//        browser {
//            binaries.executable()
//            commonWebpackConfig {
//                cssSupport.enabled = true
//            }
//        }
//    }
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }

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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.kotlin.serialization)
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
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
            //iosSimulatorArm64Test.dependsOn(this)
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