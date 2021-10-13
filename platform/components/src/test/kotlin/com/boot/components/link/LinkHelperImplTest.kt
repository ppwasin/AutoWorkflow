package com.boot.components.link

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LinkHelperImplTest {

  @Test
  fun appendQueryParamsWithEmptyParameters() {
    // Act
    val linkHelper = LinkHelperImpl()
    val baseLink = "agoda://hotel/Kao%20Hom%20Homestay/10708"
    val parameters = hashMapOf<String, String>()

    // Act
    val appendedUrl = linkHelper.appendQueryParams(baseLink, parameters)

    // Assert
    assertEquals("agoda://hotel/Kao%20Hom%20Homestay/10708", appendedUrl)
  }

  @Test
  fun appendQueryParams() {
    // Act
    val linkHelper = LinkHelperImpl()
    val baseLink = "agoda://hotel/Kao%20Hom%20Homestay/10708"
    val parameters = hashMapOf("x" to "1", "y" to "2", "z" to "3")

    // Act
    val appendedUrl = linkHelper.appendQueryParams(baseLink, parameters)

    // Assert
    assertEquals("agoda://hotel/Kao%20Hom%20Homestay/10708?x=1&y=2&z=3", appendedUrl)
  }

  @Test
  fun appendQueryParamsWithExistingQuery() {
    // Act
    val linkHelper = LinkHelperImpl()
    val baseLink = "agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2"
    val parameters = hashMapOf("x" to "1", "y" to "2", "z" to "3")

    // Act
    val appendedUrl = linkHelper.appendQueryParams(baseLink, parameters)

    // A
    assertEquals("agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&x=1&y=2&z=3", appendedUrl)
  }

  @Test
  fun appendQueryParamsWithEmptyLink() {
    // Act
    val linkHelper = LinkHelperImpl()
    val baseLink = ""
    val parameters = hashMapOf("x" to "1")

    // Act
    val appendedUrl = linkHelper.appendQueryParams(baseLink, parameters)

    // A
    assertEquals(appendedUrl, baseLink)
  }

  @Test
  fun appendQueryParamsWithContentNeedsEncode() {
    // Act
    val linkHelper = LinkHelperImpl()
    val baseLink = "agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2"
    val parameters = hashMapOf("Hotel Name" to "中文")

    // Act
    val appendedUrl = linkHelper.appendQueryParams(baseLink, parameters)

    // A
    assertEquals(
      "agoda://hotel/Kao%20Hom%20Homestay/10708?a=1&b=2&Hotel+Name=%E4%B8%AD%E6%96%87",
      appendedUrl
    )
  }
}
