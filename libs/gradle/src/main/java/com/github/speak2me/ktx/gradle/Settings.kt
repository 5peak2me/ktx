package com.github.speak2me.ktx.gradle

import org.gradle.api.initialization.Settings

/**
 * @since 0.0.8
 */
public fun Settings.getLocalProperty(key: String): String? =
    gradleLocalProperties(rootDir).getProperty(key)
