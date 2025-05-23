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

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.artifacts.repositories.MavenRepositoryContentDescriptor

/**
 * Tencent Cloud mirrors the Maven Central repository at
 * [https://mirrors.cloud.tencent.com/nexus/repository/maven-public/](https://mirrors.cloud.tencent.com/nexus/repository/maven-public/).
 *
 * This method is a convenience wrapper around the [RepositoryHandler.maven] method that sets the URL to the above.
 *
 * @param descriptor the configuration block for the Maven repository content descriptor.
 * @return the configured Maven repository.
 */
public fun RepositoryHandler.tencent(
  descriptor: MavenRepositoryContentDescriptor.() -> Unit = {},
): MavenArtifactRepository = maven {
  setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public")
  mavenContent(descriptor)
}

/**
 * Adds a Maven repository that is hosted by Alibaba Cloud.
 *
 * The URLs for the repositories are as follows:
 * - [https://maven.aliyun.com/repository/central](https://maven.aliyun.com/repository/central) (default Maven Central repository)
 * - [https://maven.aliyun.com/repository/google](https://maven.aliyun.com/repository/google) (Google's Maven repository)
 * - [https://maven.aliyun.com/repository/jcenter](https://maven.aliyun.com/repository/jcenter) (JFrog's JCenter repository)
 * - [https://maven.aliyun.com/repository/public](https://maven.aliyun.com/repository/public) (combines all of the above)
 *
 * @param repo the name of the repository to add.
 * @param descriptor the configuration block for the Maven repository content descriptor.
 * @return the configured Maven repository.
 */
@Suppress("SpellCheckingInspection")
public fun RepositoryHandler.aliyun(
  repo: String = "public",
  descriptor: MavenRepositoryContentDescriptor.() -> Unit = {},
): MavenArtifactRepository = maven {
  setUrl("https://maven.aliyun.com/repository/$repo")
  mavenContent(descriptor)
}

/**
 * Huawei Cloud mirrors the Maven Central repository at
 * [https://repo.huaweicloud.com/repository/maven](https://repo.huaweicloud.com/repository/maven).
 *
 * This method is a convenience wrapper around the [RepositoryHandler.maven] method that sets the URL to the above.
 *
 * @param descriptor the configuration block for the Maven repository content descriptor.
 * @return the configured Maven repository.
 */
public fun RepositoryHandler.huawei(
  descriptor: MavenRepositoryContentDescriptor.() -> Unit = {},
): MavenArtifactRepository = maven {
  setUrl("https://repo.huaweicloud.com/repository/maven")
  mavenContent(descriptor)
}

/**
 * Adds the JitPack Maven repository to the repository handler.
 *
 * JitPack is a unique package repository for GitHub projects. It allows you to use any
 * GitHub repository as a dependency in your build script. The URL for the repository is
 * [https://jitpack.io](https://jitpack.io).
 *
 * @param descriptor the configuration block for the Maven repository content descriptor.
 */
public fun RepositoryHandler.jitpack(
  descriptor: MavenRepositoryContentDescriptor.() -> Unit = {},
): MavenArtifactRepository = maven {
  setUrl("https://jitpack.io")
  mavenContent(descriptor)
}
