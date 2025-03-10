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
        hit <= 10 -> ScoreState("C", AppTheme.colors.onSurfaceVariant)
        hit <= 20 -> ScoreState("B", AppTheme.colors.primary)
        hit <= 25 -> ScoreState("A", AppTheme.colors.primary)
        hit <= 30 -> ScoreState("S", AppTheme.colors.secondary)
        hit <= 35 -> ScoreState("SS", AppTheme.colors.secondary)
        hit > 35 -> ScoreState("SSS", AppTheme.colors.secondary)
        else -> ScoreState("?", AppTheme.colors.onSurfaceVariant)
    }
}