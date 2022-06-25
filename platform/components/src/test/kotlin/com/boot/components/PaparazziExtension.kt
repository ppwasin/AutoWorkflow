package com.boot.components

import app.cash.paparazzi.Paparazzi
import java.lang.reflect.Method
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor
import org.junit.jupiter.api.extension.ReflectiveInvocationContext

class PaparazziExtension : InvocationInterceptor {
  private val paparazzi = Paparazzi()
  override fun interceptBeforeEachMethod(
    invocation: InvocationInterceptor.Invocation<Void>?,
    invocationContext: ReflectiveInvocationContext<Method>?,
    extensionContext: ExtensionContext?
  ) {
    super.interceptBeforeEachMethod(invocation, invocationContext, extensionContext)
    //        paparazzi.apply()
  }
}
