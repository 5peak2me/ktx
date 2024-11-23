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
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.jetbrainsKotlinAndroid) apply false
  alias(libs.plugins.jetbrainsKotlinJvm) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.dokka)
}

allprojects {
  subprojects.forEach {
    if (it.subprojects.isNotEmpty()) {
      tasks.dokkaHtmlMultiModule {
        dependsOn(it.tasks.dokkaHtmlMultiModule)
      }
    }
  }
}

subprojects {
  if (name != "app") {
    group = "com.github.5peak2me.ktx"

    plugins.apply("maven-publish")
    plugins.apply("org.jetbrains.dokka")

    tasks.withType<KotlinCompile> {
      compilerOptions {
        jvmTarget = JvmTarget.JVM_17
        freeCompilerArgs.add("-Xexplicit-api=strict")
        freeCompilerArgs.add("-opt-in=kotlin.contracts.ExperimentalContracts")
      }
    }
  }
}
