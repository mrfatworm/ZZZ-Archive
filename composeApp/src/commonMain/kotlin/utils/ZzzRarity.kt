package utils

import androidx.compose.ui.graphics.Color
import ui.theme.ColorScheme

enum class ZzzRarity(val level: Int, val color: Color) {
    One(1, ColorScheme().imageRarity1),
    Two(2, ColorScheme().imageRarity2),
    Three(3, ColorScheme().imageRarity3),
    Four(4, ColorScheme().imageRarity4),
    Five(5, ColorScheme().imageRarity5)
}