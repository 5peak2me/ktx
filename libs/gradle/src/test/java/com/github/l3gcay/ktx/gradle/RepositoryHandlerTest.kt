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
