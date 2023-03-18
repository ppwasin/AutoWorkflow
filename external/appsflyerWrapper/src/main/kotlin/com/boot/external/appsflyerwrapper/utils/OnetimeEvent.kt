package com.boot.external.appsflyerwrapper.utils

import java.util.concurrent.CopyOnWriteArraySet

class OneTimeEvent<out T>(private val content: T) {

	private val handlers = CopyOnWriteArraySet<String>()

	/**
	 * @param asker Used to identify, whether this "asker" has already handled
	 * this Event.
	 *
	 * @return Event content or null if it has been already handled by asker
	 */
	fun getIfNotHandled(asker: String): T? =
		if (handlers.add(asker)) content else null
}
