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
package com.github.speak2me.ktx.gradle

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

/**
 * copy from [com.android.build.gradle.internal.cxx.configure.gradleLocalProperties]
 */
@PublishedApi
internal fun gradleLocalProperties(projectRootDir: File): Properties {
  val properties = Properties()
  val localProperties =
    File(projectRootDir, "local.properties") // com.android.SdkConstants.FN_LOCAL_PROPERTIES

  if (localProperties.isFile) {
    InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
      properties.load(reader)
    }
  }
  return properties
}

@PublishedApi
internal inline fun <reified T> String.convert(default: T): T {
  if (this is T) return this
  val result: Any? = when (T::class) {
    Boolean::class -> toBooleanStrictOrNull() ?: toBoolean()
    Byte::class -> toByteOrNull()
    Short::class -> toShortOrNull()
    Int::class -> toIntOrNull()
    Float::class -> toFloatOrNull()
    Long::class -> toLongOrNull()
    Double::class -> toDoubleOrNull()
    else -> null
  }
  return (result as? T) ?: default
}
