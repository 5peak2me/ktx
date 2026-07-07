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
package com.github.speak2me.ktx.gradle

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertTrue
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.plugins.signing.SigningExtension
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import java.nio.file.Paths

internal class ProjectTest {

  @Test
  fun `test libs`() {
    val project = ProjectBuilder.builder().build()
    project.afterEvaluate {
      assertTrue(
        project.extensions.findByType(VersionCatalogsExtension::class.java)
          ?.named("libs") == project.libs,
      )
    }
  }

  @Test
  fun `test gradleSigning`() {
    val project = ProjectBuilder.builder().build()
    project.pluginManager.apply("signing")

    assertTrue(project.extensions.findByType(SigningExtension::class.java) == project.gradleSigning)
  }

  @Test
  fun `test gradlePublishing`() {
    val project = ProjectBuilder.builder().build()
    project.pluginManager.apply("maven-publish")

    assertTrue(project.extensions.findByType(PublishingExtension::class.java) == project.gradlePublishing)
  }

  @Test
  fun `gradleGeneratedAccessorsClasspath includes catalog accessor superclass classpath`() {
    val project = ProjectBuilder.builder().build()
    val expectedFile = Paths.get(
      GeneratedAccessorBase::class.java.protectionDomain.codeSource.location.toURI(),
    ).toFile()

    val classpath = project.gradleGeneratedAccessorsClasspath(
      script = "",
      CatalogAccessor(),
    )

    assertThat(classpath.files).containsExactly(expectedFile)
  }

  @Test
  fun `gradleGeneratedAccessorsClasspath removes duplicate accessor classpaths`() {
    val project = ProjectBuilder.builder().build()
    val expectedFile = Paths.get(
      GeneratedAccessorBase::class.java.protectionDomain.codeSource.location.toURI(),
    ).toFile()

    val classpath = project.gradleGeneratedAccessorsClasspath(
      script = "",
      CatalogAccessor(),
      AnotherCatalogAccessor(),
    )

    assertThat(classpath.files).containsExactly(expectedFile)
  }

  @Test
  fun `gradleGeneratedAccessorsClasspath is empty without generated accessor locations`() {
    val project = ProjectBuilder.builder().build()

    val classpath = project.gradleGeneratedAccessorsClasspath(script = "")

    assertThat(classpath.files).isEmpty()
  }

  private open class GeneratedAccessorBase

  private class CatalogAccessor : GeneratedAccessorBase()

  private class AnotherCatalogAccessor : GeneratedAccessorBase()
}
