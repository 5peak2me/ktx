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

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.file.FileCollection
import org.gradle.api.publish.PublishingExtension
import org.gradle.plugins.signing.SigningExtension
import java.net.URL
import java.nio.file.Paths

public inline val Project.libs: LibrariesForLibs
  get() = extensions.getByType(LibrariesForLibs::class.java)

public inline val Project.versionCatalogs: VersionCatalogsExtension
  get() = extensions.getByType(VersionCatalogsExtension::class.java)

public inline val Project.gradleSigning: SigningExtension
  get() = extensions.getByType(SigningExtension::class.java)

public inline val Project.gradlePublishing: PublishingExtension
  get() = extensions.getByType(PublishingExtension::class.java)

public fun Project.gradleGeneratedAccessorsClasspath(
  script: Any,
  vararg catalogAccessors: Any,
): FileCollection {
  val urls = linkedSetOf<URL>()

  catalogAccessors.forEach { accessor ->
    accessor.javaClass.superclass
      ?.protectionDomain
      ?.codeSource
      ?.location
      ?.let(urls::add)
  }

  val scriptClassLoader = script.javaClass.classLoader

  runCatching {
    scriptClassLoader.loadClass("org.gradle.kotlin.dsl.ImplementationConfigurationAccessorsKt")
  }.getOrNull()
    ?.protectionDomain
    ?.codeSource
    ?.location
    ?.let(urls::add)

  return files(
    urls.map { Paths.get(it.toURI()).toFile() }
  )
}
