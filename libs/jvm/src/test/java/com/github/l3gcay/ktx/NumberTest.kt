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
