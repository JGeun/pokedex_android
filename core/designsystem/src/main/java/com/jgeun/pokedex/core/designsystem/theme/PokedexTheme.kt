@file:OptIn(ExperimentalComposeUiApi::class)

package com.jgeun.pokedex.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

/**
 * Local providers for various properties we connect to our components, for styling.
 */
private val LocalColors = compositionLocalOf<PokedexColors> {
  error("No colors provided! Make sure to wrap all usages of Pokedex components in PokedexTheme.")
}

@Composable
public fun PokedexTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  colors: PokedexColors = if (darkTheme) {
    PokedexColors.defaultDarkColors()
  } else {
    PokedexColors.defaultLightColors()
  },
  background: PokdexBackground = PokdexBackground.defaultBackground(darkTheme),
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(
    LocalColors provides colors,
    LocalBackgroundTheme provides background,
  ) {
    Box(
      modifier = Modifier
        .background(background.color)
        .semantics { testTagsAsResourceId = true },
    ) {
      content()
    }
  }
}

/**
 * Contains ease-of-use accessors for different properties used to style and customize the app
 * look and feel.
 */
public object PokedexTheme {
  /**
   * Retrieves the current [PokedexColors] at the call site's position in the hierarchy.
   */
  public val colors: PokedexColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

  /**
   * Retrieves the current [PokdexBackground] at the call site's position in the hierarchy.
   */
  public val background: PokdexBackground
    @Composable
    @ReadOnlyComposable
    get() = LocalBackgroundTheme.current
}
