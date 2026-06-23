# ktx

[![](https://jitpack.io/v/5peak2me/ktx.svg)](https://jitpack.io/#5peak2me/ktx)
[![Kotlin](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/5peak2me/ktx/main/gradle/libs.versions.toml&query=%24.versions.kotlin&label=Kotlin&color=blue&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/5peak2me/ktx/main/gradle/libs.versions.toml&query=%24.versions.agp&label=AGP&color=blue&logo=android&logoColor=white)](https://developer.android.com/build/releases/gradle-plugin)
[![compileSdk](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/5peak2me/ktx/main/gradle/libs.versions.toml&query=%24.versions.compileSdk&label=compileSdk&color=green&logo=android&logoColor=white)](https://developer.android.com/guide/)
[![minSdk](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/5peak2me/ktx/main/gradle/libs.versions.toml&query=%24.versions.minSdk&label=minSdk&color=green&logo=android&logoColor=white)](https://developer.android.com/guide/)

Small Kotlin extension libraries for JVM, Android, and Gradle builds.

## Modules

| Module  | Coordinate                              | Purpose                                                                                           |
|---------|-----------------------------------------|---------------------------------------------------------------------------------------------------|
| JVM     | `com.github.5peak2me.ktx:jvm:0.0.5`     | General Kotlin extensions for strings, numbers, collections, booleans, and nullable values.       |
| Android | `com.github.5peak2me.ktx:android:0.0.5` | Android extensions for context resources, dimensions, colors, bundles, intents, and theme state.  |
| Gradle  | `com.github.5peak2me.ktx:gradle:0.0.5`  | Gradle Kotlin DSL helpers for repositories, version catalogs, providers, publishing, and signing. |

## Installation

Add JitPack to dependency resolution:

```kotlin
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
  }
}
```

Then add the modules you need:

```kotlin
dependencies {
  implementation("com.github.5peak2me.ktx:jvm:0.0.5")
  implementation("com.github.5peak2me.ktx:android:0.0.5")
  implementation("com.github.5peak2me.ktx:gradle:0.0.5")
}
```

Groovy DSL:

```groovy
dependencies {
  implementation "com.github.5peak2me.ktx:jvm:0.0.5"
  implementation "com.github.5peak2me.ktx:android:0.0.5"
  implementation "com.github.5peak2me.ktx:gradle:0.0.5"
}
```

## License

Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
