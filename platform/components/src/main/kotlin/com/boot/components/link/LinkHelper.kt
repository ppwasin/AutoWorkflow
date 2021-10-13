package com.boot.components.link

interface LinkHelper {
  fun appendQueryParams(baseLink: String, parameters: Map<String, String>): String
}