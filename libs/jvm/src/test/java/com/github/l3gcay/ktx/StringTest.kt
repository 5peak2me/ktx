package com.github.l3gcay.ktx

import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class StringTest {

    @Test
    fun `test capitalize`() {
        assertEquals("Hello World", "hello World".capitalize)
    }

    @Test
    fun `test decapitalize`() {
        assertEquals("hello world", "Hello world".decapitalize)
    }

}
