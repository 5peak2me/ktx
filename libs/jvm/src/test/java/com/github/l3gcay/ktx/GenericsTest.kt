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

import org.junit.Assert.assertEquals
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

  @Test
  fun `test isNonNull()`() {
    val str: String? = null
    if (str.isNonNull()) {
      assertEquals(str.javaClass, String::class.java)
    }
  }
}
