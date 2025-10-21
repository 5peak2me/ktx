package com.github.l3gcay.ktx.gradle

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency
import java.util.Optional

context(Project)
public fun Provider<PluginDependency>.toDependency() = map {
    project.dependencyFactory.create(it.pluginId, "${it.pluginId}.gradle.plugin", it.version.toString())
}

context(Project)
public fun Optional<Provider<PluginDependency>>.toDependency() = get().toDependency()

context(Project)
public fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }

context(Project)
public fun Provider<*>.relativeToRootProject(dir: String) = flatMap {
  rootProject.layout.buildDirectory.dir(projectDir.toRelativeString(rootDir))
}.map { it.dir(dir) }

context(Project)
public fun Provider<*>.relativeToProject(dir: String) = map { project.layout.buildDirectory.dir(dir) }
