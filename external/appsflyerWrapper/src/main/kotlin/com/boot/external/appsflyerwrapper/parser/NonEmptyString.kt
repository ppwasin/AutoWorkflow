package com.boot.external.appsflyerwrapper.parser

@JvmInline
value class NonEmptyString private constructor(val value: String) {
  companion object {
    fun createOrThrow(string: String?): NonEmptyString {
      if (string.isNullOrEmpty())
        throw IllegalArgumentException(
          "NonEmptyString cannot have null or empty value",
        )
      return NonEmptyString(string)
    }
  }
}
