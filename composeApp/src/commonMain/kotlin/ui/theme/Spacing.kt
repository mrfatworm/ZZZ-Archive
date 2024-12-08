/*
 * Copyright (c) 2021 Felipe Castilhos
 * License: MIT License
 * Modify by mrfatworm 2024
 */

package ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Ref:
 * [felipecastilhos / pokedex-android](https://github.com/felipecastilhos/pokedex-android)
 */
class Spacing(
    val s100: Dp,
    val s200: Dp,
    val s250: Dp,
    val s300: Dp,
    val s350: Dp,
    val s400: Dp,
    val s450: Dp,
    val s500: Dp,
) {
    companion object {
        fun regular() = Spacing(
            s100 = 2.dp,
            s200 = 4.dp,
            s250 = 6.dp,
            s300 = 8.dp,
            s350 = 12.dp,
            s400 = 16.dp,
            s450 = 24.dp,
            s500 = 32.dp,
        )
    }
}