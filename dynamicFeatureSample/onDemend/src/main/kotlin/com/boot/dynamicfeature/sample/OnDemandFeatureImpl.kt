package com.boot.dynamicfeature.sample

import com.boot.dynamicfeature.contract.OnDemandFeature
import com.google.auto.service.AutoService

@AutoService(OnDemandFeature.EntryPoint::class)
class OnDemandFeatureImpl: OnDemandFeature.EntryPoint {
	override fun getSomething(): String {
		return "Hello from OnDemandFeature"
	}
}
