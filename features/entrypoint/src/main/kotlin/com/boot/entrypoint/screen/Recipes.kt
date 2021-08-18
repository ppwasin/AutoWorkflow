package com.boot.entrypoint.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

fun getSubstrings(substring: String, text: String): List<IntRange> {
  return substring.toRegex().findAll(text).map { it.range }.toList()
}

@Composable
fun Recipes() {
  val name = "Espresso"
  val italicSubstring = "coffee"
  val description =
    "Espresso is coffee of Italian origin, brewed by forcing a small amount of nearly boiling water under pressure (expressing) through finely-ground coffee beans."

  val substrings = getSubstrings(italicSubstring, description)
  val nameIndex = description.indexOf(name)

  Text(
    text =
      buildAnnotatedString {
        append(description)
        addStyle(
          style = SpanStyle(fontWeight = FontWeight.Bold),
          start = nameIndex,
          end = nameIndex + name.length
        )

        for (substringRange in substrings) {
          addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = substringRange.first,
            end = substringRange.last + 1
          )
        }
      }
  )
}

@Preview
@Composable
fun RecipesPreview() {
  Recipes()
}
