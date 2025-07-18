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
plugins {
  id("java-library")
  `kotlin-dsl`
  alias(libs.plugins.jetbrains.kotlin.jvm)
  alias(libs.plugins.jetbrains.kotlinx.binary.compatibility)
  id("jacoco")
}

publishing {
  publications {
    register("release", MavenPublication::class) {
      from(components["java"])
    }
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17

  withSourcesJar()
}

dependencies {
  implementation(gradleApi())
  implementation(project(":libs:jvm"))
  testImplementation(libs.junit)
}
