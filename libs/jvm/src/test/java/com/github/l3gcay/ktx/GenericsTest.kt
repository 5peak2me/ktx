package com.github.l3gcay.ktx

import org.junit.Test

internal class GenericsTest {

  @Test
  fun `test isNull`() {
    val str: String? = null
    assert(str.isNull)
  }

  @Test
  fun `test isNotNull`() {
    val str = "abc"
    assert(str.isNotNull)
  }

}
