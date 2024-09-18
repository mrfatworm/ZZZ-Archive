/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class ColorScheme(
    val primary: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val primaryContainer: Color = Color.Unspecified,
    val onPrimaryContainer: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val border: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val surfaceContainer: Color = Color.Unspecified,
    val onSurfaceContainer: Color = Color.Unspecified,
    val onSurfaceVariant: Color = Color.Unspecified,
    val alert: Color = Color.Unspecified,
    val onAlert: Color = Color.Unspecified,
    val imageBorder: Color = Color.Unspecified,
    val imageInsideBorder: Color = Color.Unspecified,
    val imageBackground: Color = Color.Unspecified,
    val imageTagContainer: Color = Color.Unspecified,
    val imageOnTagContainer: Color = Color.Unspecified,
    val imageRarity1: Color = Color.Unspecified,
    val imageRarity2: Color = Color.Unspecified,
    val imageRarity3: Color = Color.Unspecified,
    val imageRarity4: Color = Color.Unspecified,
    val imageRarity5: Color = Color.Unspecified
)

internal val darkScheme = ColorScheme(
    primary = primary500,
    onPrimary = primary950,
    primaryContainer = primary900,
    onPrimaryContainer = primary100,
    secondary = secondary500,
    onSecondary = secondary950,
    border = neutral700,
    surface = neutral950,
    onSurface = neutral200,
    surfaceContainer = neutral900,
    onSurfaceContainer = neutral100,
    onSurfaceVariant = neutral400,
    alert = alert700,
    onAlert = alert200,
    imageBorder = neutral700,
    imageInsideBorder = neutral900,
    imageBackground = neutral950,
    imageTagContainer = Color(0x80151515),
    imageOnTagContainer = Color(0xFFF0F0F0),
    imageRarity1 = Color(0xFF838282),
    imageRarity2 = Color(0xFF7DA89B),
    imageRarity3 = Color(0xFF00A9FF),
    imageRarity4 = Color(0xFFBB37C7),
    imageRarity5 = Color(0xFFFFB500)
)

internal val lightScheme = ColorScheme(
    primary = primary600,
    onPrimary = primary50,
    primaryContainer = primary200,
    onPrimaryContainer = primary900,
    secondary = secondary50,
    onSecondary = secondary950,
    border = neutral100,
    surface = neutral100,
    onSurface = neutral800,
    surfaceContainer = neutral50,
    onSurfaceContainer = neutral800,
    onSurfaceVariant = neutral600,
    alert = alert600,
    onAlert = alert100,
    imageBorder = neutral50,
    imageInsideBorder = neutral100,
    imageBackground = neutral100,
    imageTagContainer = Color(0x80151515),
    imageOnTagContainer = Color(0xFFF0F0F0),
    imageRarity1 = Color(0xFF838282),
    imageRarity2 = Color(0xFF7DA89B),
    imageRarity3 = Color(0xFF00A9FF),
    imageRarity4 = Color(0xFFDA4EE8),
    imageRarity5 = Color(0xFFFFB500)
)
