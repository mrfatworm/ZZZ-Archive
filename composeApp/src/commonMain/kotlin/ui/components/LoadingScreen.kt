/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_bangboo
import zzzarchive.composeapp.generated.resources.loading

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            AppTheme.spacing.s400,
            Alignment.CenterVertically
        )
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "Rotate Animation")
        val rotateAnimation = infiniteTransition.animateFloat(
            initialValue = -60f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        )
        Icon(
            modifier = Modifier.size(96.dp).graphicsLayer {
                rotationZ = rotateAnimation.value
            },
            imageVector = vectorResource(Res.drawable.ic_bangboo),
            contentDescription = "Bangboo Gu Lu Gu Lu",
            tint = AppTheme.colors.onSurfaceVariant
        )
        Text(
            text = stringResource(Res.string.loading),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.headlineMedium
        )
    }
}