/*
 * Copyright © 2023 J!nl!n™ Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.l3gcay.ktx

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.Locale

@RunWith(Parameterized::class)
internal class NumberTest(private val input: Number?, private val expected: Number) {

  @Test
  fun `test orZero`() {
    assertEquals(expected, input.orZero()) // 验证 orZero 的返回值
  }

  companion object {
    @JvmStatic
    @Parameterized.Parameters
    fun data(): List<Array<out Any?>> = listOf(
      arrayOf(1, 1),
      arrayOf(0, 0),
      arrayOf(-100, -100),
      arrayOf(null, 0),
      arrayOf(1.1f, 1.1f),
      arrayOf(0.0f, 0f),
      arrayOf(2.3, 2.3),
      arrayOf(0.0, 0.0),
      arrayOf(null, 0),
    )
  }

  @Test
  fun `test toCurrency`() {
    val number = 1.2345678912345679E8
    assertEquals("¥123,456,789.12", number.toCurrency())
    assertEquals("$123,456,789.12", number.toCurrency(Locale.US))
    assertEquals("£123,456,789.12", number.toCurrency(Locale.UK))
    assertEquals("$123,456,789.12", number.toCurrency(Locale.CANADA))
    assertEquals("₹123,456,789.12", number.toCurrency(Locale.of("en", "IN")))
    assertEquals("€123,456,789.12", number.toCurrency(Locale.of("en", "IE")))
    assertEquals("$123,456,789.12", number.toCurrency(Locale.of("en", "NZ")))
    assertEquals("R123 456 789,12", number.toCurrency(Locale.of("en", "ZA")))
    assertEquals("$123,456,789.12", number.toCurrency(Locale.of("en", "JM")))
    assertEquals("RM123,456,789.12", number.toCurrency(Locale.of("en", "MY")))
    assertEquals("₱123,456,789.12", number.toCurrency(Locale.of("en", "PH")))
    assertEquals("$123,456,789.12", number.toCurrency(Locale.of("en", "TT")))
    assertEquals("HK$123,456,789.12", number.toCurrency(Locale.of("en", "HK")))

    val number2 = 100000000
    assertEquals("¥100,000,000.00", number2.toCurrency(Locale.CHINA))
    assertEquals("$100,000,000.00", number2.toCurrency(Locale.US))

    val number3 = 10000.123F
    assertEquals("¥10,000.12", number3.toCurrency(Locale.of("zh", "CN")))
    assertEquals("$10,000.12", number3.toCurrency(Locale.of("en", "US")))

    assertEquals("¥0.00", null.toCurrency())
  }

  @Test
  fun `test toPercent`() {
    val number = 0.12345678912345678
    assertEquals("12%", number.toPercent())
    assertEquals("12%", number.toPercent(Locale.CHINA))

    assertEquals("0%", null.toPercent())
    assertEquals("0%", null.toPercent(Locale.CHINA))

    val zero = 0
    assertEquals("0%", zero.toPercent(Locale.US))

    val number2 = 134L
    assertEquals("13,400%", number2.toPercent())
  }
}
