/*
 * Copyright © 2026 J!nl!n™ Inc. All rights reserved.
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
import org.apache.tools.ant.taskdefs.condition.Os
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
  alias(libs.plugins.jetbrains.kotlin.multiplatform)
  alias(libs.plugins.jetbrains.kotlin.plugin.compose)
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.android.kotlin.multiplatform.library)
  alias(libs.plugins.android.lint)
}

kotlin {
  @OptIn(ExperimentalAbiValidation::class)
  abiValidation()

  compilerOptions {
    explicitApi()
  }

  // Target declarations - add or remove as needed below. These define
  // which platforms this KMP module supports.
  // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
  android {
    namespace = "com.github.speak2me.ktx.compose"
    compileSdk {
      version = release(37)
    }
    minSdk = 24

    withHostTestBuilder {
    }

    withDeviceTestBuilder {
      sourceSetTreeName = "test"
    }.configure {
      instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
      baseline = file("lint-baseline.xml")
    }
  }

  // For iOS targets, this is also where you should
  // configure native binary output. For more information, see:
  // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

  // A step-by-step guide on how to include this library in an XCode
  // project can be found here:
  // https://developer.android.com/kotlin/multiplatform/migrate
  val xcfName = "composeKit"

  buildList {
    if (Os.isFamily(Os.FAMILY_MAC)) {
      if (Os.isArch("arm64") || Os.isArch("aarch64")) {
        add(iosArm64())
        add(iosSimulatorArm64())
      } else {
        add(iosX64())
      }
    }
  }.forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = xcfName
    }
  }

  // Source set declarations.
  // Declaring a target automatically creates a source set with the same name. By default, the
  // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
  // common to share sources between related targets.
  // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
  sourceSets {
    commonMain {
      dependencies {
        implementation(libs.kotlin.stdlib)
        // Add KMP dependencies here
        implementation(libs.compose.runtime)
        implementation(libs.compose.foundation)
        implementation(libs.compose.material3)
        implementation(libs.compose.ui)
        implementation(libs.compose.components.resources)
        implementation(libs.compose.uiToolingPreview)
        implementation(libs.androidx.lifecycle.viewmodelCompose)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        implementation(libs.androidx.paging.compose)
      }
    }

    commonTest {
      dependencies {
        implementation(libs.kotlin.test)
      }
    }

    androidMain {
      dependencies {
        // Add Android-specific dependencies here. Note that this source set depends on
        // commonMain by default and will correctly pull the Android artifacts of any KMP
        // dependencies declared in commonMain.
        implementation(libs.compose.uiToolingPreview)
      }
    }

    getByName("androidDeviceTest") {
      dependencies {
        implementation(libs.androidx.core)
        implementation(libs.androidx.junit)
        implementation(libs.androidx.runner)
      }
    }

    iosMain {
      dependencies {
        // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
        // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
        // part of KMP’s default source set hierarchy. Note that this source set depends
        // on common by default and will correctly pull the iOS artifacts of any
        // KMP dependencies declared in commonMain.
      }
    }
  }

}

dependencies {
  androidRuntimeClasspath(libs.compose.uiTooling)
}
