package utils

import androidx.compose.ui.graphics.Color
import ui.theme.ColorScheme

enum class ZzzRarity(val level: Int, val code: String, val color: Color) {
    RANK_D(1, "D", ColorScheme().imageRarity1),
    RANK_C(2, "C", ColorScheme().imageRarity2),
    RANK_B(3, "B", ColorScheme().imageRarity3),
    RANK_A(4, "A", ColorScheme().imageRarity4),
    RANK_S(5, "S", ColorScheme().imageRarity5)
}