package com.boot.dynamicfeature.contract

/** Example
 * dynamicFeatures += setOf(":features:feature_1", ":features:feature_2")
 * moduleName = "feature_1" or "feature_2"
 *
 * See also
 * https://developer.android.com/guide/playcore/feature-delivery#feature-module-manifest
 */
@JvmInline
value class DynamicFeature(val moduleName: String)
