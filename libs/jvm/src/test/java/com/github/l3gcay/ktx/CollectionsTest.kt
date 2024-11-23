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
import java.util.Collections

internal class CollectionsTest {

  @Test
  fun `test Collection orDefault`() {
    val list = listOf(1, 2, 3)
    assert(Collections.EMPTY_LIST.orDefault(list) == list)
    assert(list.orDefault(Collections.emptyList()) == list)
    assert(null.orDefault(list) == list)
  }

  @Test
  fun `test Map orDefault`() {
    val map1 = mapOf(1 to "1", 2 to "2", 3 to "3")
    assert(Collections.emptyMap<Int, String>().orDefault(map1) == map1)
    assert(map1.orDefault(Collections.emptyMap()) == map1)
    assert(null.orDefault(map1) == map1)
  }
}
