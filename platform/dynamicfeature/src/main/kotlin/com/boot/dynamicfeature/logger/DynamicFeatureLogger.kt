package com.boot.dynamicfeature.logger

import java.util.logging.Logger

object DynamicFeatureLogger {
	private val logger = Logger.getLogger("DynamicFeature")
	fun log(msg: String){
		logger.info(msg)
	}
}
