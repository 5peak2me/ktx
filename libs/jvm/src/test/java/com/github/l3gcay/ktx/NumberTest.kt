package com.github.l3gcay.ktx

import org.junit.Test

internal class NumberTest {

  @Test
  fun `test Number orZero`() {
    val number = 1
    assert(number.orZero() == 1)

    val number1: Byte? = null
    assert(number1.orZero() == 0)

    val number2: Short? = null
    assert(number2.orZero() == 0)

    val number3: Int? = null
    assert(number3.orZero() == 0)

    val number4: Long? = null
    assert(number4.orZero() == 0)

    val number5: Float? = null
    assert(number5.orZero() == 0)

    val number6: Double? = null
    assert(number6.orZero() == 0)
  }

}
