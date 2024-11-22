package com.github.l3gcay.ktx

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class BooleanTest {

  @Test
  fun `test toInt`() {
    assert(true.toInt() == 1)
    assert(false.toInt() == 0)
    assertTrue(true.toInt() == 1)
  }

  @Test
  fun `test toggle`() {
    assertFalse(true.toggle())
    assertTrue(false.toggle())
  }

}
