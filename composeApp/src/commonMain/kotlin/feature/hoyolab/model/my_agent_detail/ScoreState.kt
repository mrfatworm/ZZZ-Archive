/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.theme.AppTheme

data class ScoreState(val symbol: String, val color: Color)

@Composable
fun getScoreState(hit: Int): ScoreState {
    return when {
        hit <= 10 -> ScoreState("B", AppTheme.colors.onSurfaceVariant)
        hit <= 20 -> ScoreState("A", AppTheme.colors.primary)
        hit <= 25 -> ScoreState("S", AppTheme.colors.secondary)
        hit > 25 -> ScoreState("SS", AppTheme.colors.secondary)
        else -> ScoreState("?", AppTheme.colors.onSurfaceVariant)
    }
}