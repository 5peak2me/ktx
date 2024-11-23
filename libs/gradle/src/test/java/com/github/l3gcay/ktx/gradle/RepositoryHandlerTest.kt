package com.github.l3gcay.ktx.gradle

import junit.framework.TestCase.assertEquals
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

internal class RepositoryHandlerTest {

  @Test
  fun `test maven`() {
    val project = ProjectBuilder.builder().build()

    val size = project.repositories.size

    project.repositories.apply {
      tencent()
      aliyun()
      huawei()
      jitpack()
    }

    assertEquals(size, project.repositories.size - 4)
  }
}
