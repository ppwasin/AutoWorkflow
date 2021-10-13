package com.boot.components.utils

import com.boot.components.link.LinkHelper
import com.boot.components.link.LinkHelperImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class UriBuilderContractTest {
  private val uriString = "https://www.ex.com"
  private val linkHelper: LinkHelper = LinkHelperImpl()

  @Test
  fun couldAppendQueryParam() {
    val newUri = linkHelper.appendQueryParams(uriString, mapOf("key" to "value"))
    Assertions.assertEquals("https://www.ex.com?key=value", newUri)
  }
}
