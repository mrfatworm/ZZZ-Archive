package utils

import androidx.compose.ui.graphics.Color
import ui.theme.ColorScheme

enum class ZzzRarity(val level: Int, val code: String) {
    RARITY_D(1, "D"),
    RARITY_C(2, "C"),
    RARITY_B(3, "B"),
    RARITY_A(4, "A"),
    RARITY_S(5, "S");

    fun getColor(colorScheme: ColorScheme): Color {
        return when (this) {
            RARITY_D -> colorScheme.rarityD
            RARITY_C -> colorScheme.rarityC
            RARITY_B -> colorScheme.rarityB
            RARITY_A -> colorScheme.rarityA
            RARITY_S -> colorScheme.rarityS
        }
    }
}

fun findRarity(level: Int): ZzzRarity {
    return ZzzRarity.entries.find { it.level == level } ?: ZzzRarity.RARITY_D
}