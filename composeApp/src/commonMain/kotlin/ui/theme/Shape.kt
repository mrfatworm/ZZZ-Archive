/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * Ref:
 * [felipecastilhos / pokedex-android](https://github.com/felipecastilhos/pokedex-android)
 */
data class Shape(
    val r100: RoundedCornerShape,
    val r200: RoundedCornerShape,
    val r250: RoundedCornerShape,
    val r300: RoundedCornerShape,
    val r350: RoundedCornerShape,
    val r400: RoundedCornerShape,
    val r450: RoundedCornerShape,
    val r500: RoundedCornerShape,
    val round: RoundedCornerShape = CircleShape,
    val cardShape: RoundedCornerShape
) {
    companion object {
        fun regular() = Shape(
            r100 = RoundedCornerShape(2.dp),
            r200 = RoundedCornerShape(4.dp),
            r250 = RoundedCornerShape(6.dp),
            r300 = RoundedCornerShape(8.dp),
            r350 = RoundedCornerShape(12.dp),
            r400 = RoundedCornerShape(16.dp),
            r450 = RoundedCornerShape(24.dp),
            r500 = RoundedCornerShape(32.dp),
            cardShape = RoundedCornerShape(16.dp)
        )
    }
}
