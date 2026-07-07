package com.github.speak2me.ktx.gradle

import com.google.common.truth.Truth.assertThat
import org.gradle.api.provider.Provider
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

public class ProviderTest {

  private val project = ProjectBuilder.builder().build()
  private val providers = project.providers

  @Test
  public fun `convert should handle Boolean`() {
    assertThat(providers.provider { "true" }.convert(false).get()).isTrue()
    assertThat(providers.provider { "false" }.convert(true).get()).isFalse()
    // toBoolean() returns false for anything not equal to "true" (case-insensitive)
    assertThat(providers.provider { "invalid" }.convert(true).get()).isFalse()
  }

  @Test
  public fun `convert should handle Int`() {
    assertThat(providers.provider { "123" }.convert(0).get()).isEqualTo(123)
    assertThat(providers.provider { "abc" }.convert(456).get()).isEqualTo(456)
  }

  @Test
  public fun `convert should handle Long`() {
    assertThat(providers.provider { "123456789" }.convert(0L).get()).isEqualTo(123456789L)
    assertThat(providers.provider { "abc" }.convert(999L).get()).isEqualTo(999L)
  }

  @Test
  public fun `convert should handle Float`() {
    assertThat(providers.provider { "1.23" }.convert(0f).get()).isEqualTo(1.23f)
    assertThat(providers.provider { "abc" }.convert(4.56f).get()).isEqualTo(4.56f)
  }

  @Test
  public fun `convert should handle Double`() {
    assertThat(providers.provider { "1.23456" }.convert(0.0).get()).isEqualTo(1.23456)
    assertThat(providers.provider { "abc" }.convert(7.89).get()).isEqualTo(7.89)
  }

  @Test
  public fun `convert should handle String`() {
    val stringProvider: Provider<String> = providers.provider { "hello" }
    assertThat(stringProvider.convert("default").get()).isEqualTo("hello")

    val nullProvider: Provider<String> = providers.provider { null }
    assertThat(nullProvider.convert("default").get()).isEqualTo("default")
  }

  @Test
  public fun `onlyIfTrue should filter provider value`() {
    // Use context parameter support
    val project = this.project
    val trueProvider: Provider<String> = providers.provider { "true" }
    val falseProvider: Provider<String> = providers.provider { "false" }
    val invalidProvider: Provider<String> = providers.provider { "abc" }

    with(project) {
      assertThat(trueProvider.onlyIfTrue().isPresent).isTrue()
      assertThat(trueProvider.onlyIfTrue().get()).isEqualTo("true")

      assertThat(falseProvider.onlyIfTrue().isPresent).isFalse()
      assertThat(invalidProvider.onlyIfTrue().isPresent).isFalse()
    }
  }
}
