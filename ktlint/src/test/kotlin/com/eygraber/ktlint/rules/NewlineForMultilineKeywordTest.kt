package com.eygraber.ktlint.rules

import com.pinterest.ktlint.test.KtLintAssertThat.Companion.assertThatRule
import com.pinterest.ktlint.test.LintViolation
import org.junit.Test

class NewlineForMultilineKeywordTest {
  private val wrappedRule = assertThatRule { NewlineForMultilineKeyword() }

  // IF-ELSE tests

  @Test
  fun `else on new line should pass`() {
    val code = """
    fun foo() {
      if(true) {
      }
      else {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasNoLintViolations()
  }

  @Test
  fun `else on same line as closing brace should fail and be autocorrected`() {
    val code = """
    fun foo() {
      if(true) {
      } else {
      }
    }
    """.trimIndent()
    val expected = """
    fun foo() {
      if(true) {
      }
      else {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasLintViolation(3, 5, "Expected newline before 'else'")
      .isFormattedAs(expected)
  }

  // ELSE IF tests

  @Test
  fun `else if on new line should pass`() {
    val code = """
    fun foo() {
      if(true) {
      }
      else if(false) {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasNoLintViolations()
  }

  @Test
  fun `else if on same line as closing brace should fail and be autocorrected`() {
    val code = """
    fun foo() {
      if(true) {
      } else if(false) {
      }
    }
    """.trimIndent()
    val expected = """
    fun foo() {
      if(true) {
      }
      else if(false) {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasLintViolation(3, 5, "Expected newline before 'else'")
      .isFormattedAs(expected)
  }

  // TRY-CATCH tests

  @Test
  fun `catch on new line should pass`() {
    val code = """
    fun foo() {
      try {
      }
      catch(e: Exception) {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasNoLintViolations()
  }

  @Test
  fun `catch on same line as closing brace should fail and be autocorrected`() {
    val code = """
    fun foo() {
      try {
      } catch(e: Exception) {
      }
    }
    """.trimIndent()
    val expected = """
    fun foo() {
      try {
      }
      catch(e: Exception) {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasLintViolation(3, 5, "Expected newline before 'catch'")
      .isFormattedAs(expected)
  }

  // TRY-FINALLY tests

  @Test
  fun `finally on new line should pass`() {
    val code = """
    fun foo() {
      try {
      }
      finally {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasNoLintViolations()
  }

  @Test
  fun `finally on same line as closing brace should fail and be autocorrected`() {
    val code = """
    fun foo() {
      try {
      } finally {
      }
    }
    """.trimIndent()
    val expected = """
    fun foo() {
      try {
      }
      finally {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasLintViolation(3, 5, "Expected newline before 'finally'")
      .isFormattedAs(expected)
  }

  // TRY-CATCH-FINALLY tests

  @Test
  fun `catch and finally on new lines should pass`() {
    val code = """
    fun foo() {
      try {
      }
      catch(e: Exception) {
      }
      finally {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasNoLintViolations()
  }

  @Test
  fun `catch and finally on same lines should fail and be autocorrected`() {
    val code = """
    fun foo() {
      try {
      } catch(e: Exception) {
      } finally {
      }
    }
    """.trimIndent()
    val expected = """
    fun foo() {
      try {
      }
      catch(e: Exception) {
      }
      finally {
      }
    }
    """.trimIndent()
    wrappedRule(code)
      .hasLintViolations(
        LintViolation(3, 5, "Expected newline before 'catch'"),
        LintViolation(4, 5, "Expected newline before 'finally'"),
      )
      .isFormattedAs(expected)
  }

  // Single-line expressions should be ignored

  @Test
  fun `single line if-else expression without braces should pass`() {
    val code = """
    fun foo() = if(true) 1 else 2
    """.trimIndent()
    wrappedRule(code)
      .hasNoLintViolations()
  }
}
