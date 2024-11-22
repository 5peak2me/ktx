plugins {
  id("java-library")
  alias(libs.plugins.jetbrainsKotlinJvm)
  id("jacoco")
}

version = "0.0.1"

publishing {
  publications {
    register("release", MavenPublication::class) {
      from(components["java"])
    }
  }
}

dependencies {
  testImplementation(libs.junit)
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17

  withSourcesJar()
}
