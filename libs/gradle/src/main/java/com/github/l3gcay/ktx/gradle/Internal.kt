package com.github.l3gcay.ktx.gradle

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

/**
 * copy from [com.android.build.gradle.internal.cxx.configure.gradleLocalProperties]
 */
@PublishedApi
internal fun gradleLocalProperties(projectRootDir : File) : Properties {
    val properties = Properties()
    val localProperties = File(projectRootDir, "local.properties") // com.android.SdkConstants.FN_LOCAL_PROPERTIES

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
