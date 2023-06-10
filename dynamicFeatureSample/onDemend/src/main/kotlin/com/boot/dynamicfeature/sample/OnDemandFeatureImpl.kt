package com.boot.dynamicfeature.sample

import com.boot.dynamicfeature.contract.OnDemandEntryPoint
import com.google.auto.service.AutoService

@AutoService(OnDemandEntryPoint::class)
class OnDemandFeatureImpl: OnDemandEntryPoint {
	override fun getSomething(): String {
		return "Hello from OnDemandFeature"
	}
}
