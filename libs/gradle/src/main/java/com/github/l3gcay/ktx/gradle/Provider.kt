package com.github.l3gcay.ktx.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.file.Directory
import org.gradle.api.initialization.Settings
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.plugin.use.PluginDependency

public fun Provider<PluginDependency>.toDep(): Provider<String> = map {
  "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}

context(project: Project)
public fun Provider<PluginDependency>.toDependency(): Provider<ExternalModuleDependency> = map {
  project.dependencyFactory.create(
    it.pluginId,
    "${it.pluginId}.gradle.plugin",
    it.version.toString()
  )
}

context(project: Project)
public fun Provider<String>.onlyIfTrue(): Provider<String> = with(project) {
  flatMap { provider { it.takeIf(String::toBoolean) } }
}

context(project: Project)
public fun Provider<*>.relativeToRootProject(dir: String): Provider<Directory> = with(project) {
  map {
    @Suppress("UnstableApiUsage")
    isolated.rootProject.projectDirectory
      .dir("build")
      .dir(projectDir.toRelativeString(rootDir))
  }.map { it.dir(dir) }
}

@PublishedApi
internal inline fun <reified T : Any> Provider<String>.convert(default: T): Provider<T> =
  map { it.convert(default) }.orElse(default)

public inline fun <reified T : Any> ProviderFactory.gradleProperty(
  key: String,
  default: T
): Provider<T> =
  gradleProperty(key).convert(default)

public inline fun <reified T : Any> ProviderFactory.systemProperty(
  key: String,
  default: T
): Provider<T> =
  systemProperty(key).convert(default)

context(project: Project)
public inline fun <reified T : Any> ProviderFactory.localProperty(
  key: String,
  default: T
): Provider<T> =
  provider {
    gradleLocalProperties(project.rootDir).getProperty(key)
  }.convert(default)

context(settings: Settings)
public inline fun <reified T : Any> ProviderFactory.localProperty(
  key: String,
  default: T
): Provider<T> =
  provider {
    gradleLocalProperties(settings.rootDir).getProperty(key)
  }.convert(default)
