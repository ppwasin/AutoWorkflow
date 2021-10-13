package com.boot.components.link

class BltLinkAppender(
    private val linkHelper: LinkHelper,
    private val infoQueryProvider: InfoQueryProvider
) {
  fun appendAvailableParams(uri: String): String {
    return linkHelper.appendQueryParams(uri, infoQueryProvider.getLinkQueryParams())
  }
}
