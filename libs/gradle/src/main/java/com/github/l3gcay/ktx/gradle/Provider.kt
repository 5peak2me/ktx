/*
 * Copyright © 2025 J!nl!n™ Inc. All rights reserved.
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

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency
import java.util.Optional

context(Project)
public fun Provider<PluginDependency>.toDependency(): Provider<ExternalModuleDependency> = map {
  project.dependencyFactory.create(
    it.pluginId,
    "${it.pluginId}.gradle.plugin",
    it.version.toString(),
  )
}

context(Project)
public fun Optional<Provider<PluginDependency>>.toDependency(): Provider<ExternalModuleDependency> = get().toDependency()

context(Project)
public fun Provider<String>.onlyIfTrue(): Provider<String> = flatMap { provider { it.takeIf(String::toBoolean) } }

context(Project)
public fun Provider<*>.relativeToRootProject(dir: String): Provider<Directory> = flatMap {
  rootProject.layout.buildDirectory.dir(projectDir.toRelativeString(rootDir))
}.map { it.dir(dir) }

context(Project)
public fun Provider<*>.relativeToProject(dir: String): Provider<Provider<Directory>> = map { project.layout.buildDirectory.dir(dir) }
