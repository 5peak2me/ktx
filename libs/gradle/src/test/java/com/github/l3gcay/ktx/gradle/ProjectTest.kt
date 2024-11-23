package com.github.l3gcay.ktx.gradle

import junit.framework.TestCase.assertTrue
import org.gradle.api.publish.PublishingExtension
import org.gradle.plugins.signing.SigningExtension
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

internal class ProjectTest {

  @Test
  fun `test libs`() {
    val project = ProjectBuilder.builder().build()
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

}
