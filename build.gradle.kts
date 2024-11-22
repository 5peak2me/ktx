import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    alias(libs.plugins.androidLibrary) apply false
}

subprojects {
    if (name != "app") {
        group = "com.github.5peak2me.ktx"

        plugins.apply("maven-publish")

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
            kotlinOptions.freeCompilerArgs += listOf(
                "-Xexplicit-api=strict",
                "-opt-in=kotlin.contracts.ExperimentalContracts"
            )
        }
    }
}
