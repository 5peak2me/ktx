plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("jacoco")
}

dependencies {
    testImplementation(libs.junit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
