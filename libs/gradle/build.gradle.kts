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
@file:OptIn(ExperimentalAbiValidation::class)

import org.gradle.api.tasks.testing.Test
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
  id("java-library")
  alias(libs.plugins.jetbrains.kotlin.jvm)
  id("jacoco")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11

  withSourcesJar()
}

dependencies {
  // https://stackoverflow.com/questions/76713758/use-version-catalog-inside-precompiled-gradle-plugin
  implementation(files((libs).javaClass.superclass.protectionDomain.codeSource.location))
  implementation(gradleApi())
  implementation(project(":libs:jvm"))
  testImplementation(libs.junit)
  testImplementation(libs.truth)
}

publishing {
  publications {
    register("release", MavenPublication::class) {
      from(components["java"])
    }
  }
}

tasks.withType<Test>().configureEach {
  jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
}

kotlin {
  abiValidation()
  compilerOptions {
    freeCompilerArgs.add("-Xcontext-parameters")
  }
}
