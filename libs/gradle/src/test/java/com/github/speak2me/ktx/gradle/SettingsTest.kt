/*
 * Copyright © 2026 J!nl!n™ Inc. All rights reserved.
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
package com.github.speak2me.ktx.gradle

import com.google.common.truth.Truth.assertThat
import org.gradle.api.initialization.Settings
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.lang.reflect.Proxy

internal class SettingsTest {

  @get:Rule
  val temporaryFolder = TemporaryFolder()

  @Test
  fun `getLocalProperty returns value from local properties in settings root`() {
    val rootDir = temporaryFolder.newFolder()
    File(rootDir, "local.properties").writeText(
      """
      sdk.dir=C:\\Android\\Sdk
      publish.enabled=true
      """.trimIndent(),
    )
    val settings = settingsWithRootDir(rootDir)

    assertThat(settings.getLocalProperty("publish.enabled")).isEqualTo("true")
  }

  @Test
  fun `getLocalProperty returns null when key is missing`() {
    val rootDir = temporaryFolder.newFolder()
    File(rootDir, "local.properties").writeText("publish.enabled=true")
    val settings = settingsWithRootDir(rootDir)

    assertThat(settings.getLocalProperty("missing.key")).isNull()
  }

  @Test
  fun `getLocalProperty returns null when local properties does not exist`() {
    val settings = settingsWithRootDir(temporaryFolder.newFolder())

    assertThat(settings.getLocalProperty("publish.enabled")).isNull()
  }

  private fun settingsWithRootDir(rootDir: File): Settings = Proxy.newProxyInstance(
    Settings::class.java.classLoader,
    arrayOf(Settings::class.java),
  ) { _, method, _ ->
    when (method.name) {
      "getRootDir" -> rootDir
      "toString" -> "Settings(rootDir=$rootDir)"
      else -> error("${method.name} is not supported by this test settings")
    }
  } as Settings
}
