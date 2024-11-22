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
