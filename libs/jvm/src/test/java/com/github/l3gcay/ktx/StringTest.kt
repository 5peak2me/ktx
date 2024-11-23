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

internal class StringTest {

  @Test
  fun `test capitalize`() {
    assertEquals("Hello World", "hello World".capitalize)
    assertEquals("Hello World", "Hello World".capitalize)
    assertEquals("", "".capitalize)
  }

  @Test
  fun `test decapitalize`() {
    assertEquals("hello world", "Hello world".decapitalize)
    assertEquals("hello world", "hello world".decapitalize)
    assertEquals("", "".decapitalize)
  }
}
