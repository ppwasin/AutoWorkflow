package com.modular.plugin.extensions

import com.android.build.api.dsl.*

fun <A : BuildFeatures, B : BuildType, C : DefaultConfig, D : ProductFlavor> CommonExtension<A, B, C, D>.setupCompose(composeVersion: String) {
    buildFeatures {
        compose = true

        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    packagingOptions {
        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        resources.excludes.run {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}