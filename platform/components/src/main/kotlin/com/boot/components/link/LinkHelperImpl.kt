package com.boot.components.link

import java.net.URI
import java.net.URLEncoder

class LinkHelperImpl : LinkHelper {
  override fun appendQueryParams(baseLink: String, parameters: Map<String, String>): String {
    if (baseLink.isBlank() || parameters.isEmpty()) {
      return baseLink
    }

    val firstSeparator =
      if (URI(baseLink).query.isNullOrBlank()) {
        "?"
      } else {
        "&"
      }

    return baseLink +
      firstSeparator +
      parameters.entries.joinToString("&") { e ->
        URLEncoder.encode(e.key, "utf-8") + "=" + URLEncoder.encode(e.value, "utf-8")
      }
  }
}