/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.size.Size
import ui.components.OutlinedText
import ui.components.cards.ContentCard
import ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyAgentImageCard(
    modifier: Modifier = Modifier, imageUrl: String, name: String, level: Int, rank: Int
) {
    ContentCard(modifier = modifier, hasDefaultPadding = false) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(imageUrl)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .size(Size.ORIGINAL).build(),
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(AppTheme.spacing.s400),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                OutlinedText(
                    text = name,
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.headlineMedium,
                    borderColor = AppTheme.colors.surfaceLow
                )
                OutlinedText(
                    text = "Lv. $level",
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelLarge,
                    borderColor = AppTheme.colors.surfaceLow
                )
                Text(
                    modifier = Modifier.clip(AppTheme.shape.r300)
                        .background(AppTheme.colors.onSurfaceVariant).padding(
                            horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200
                        ),
                    text = "M$rank",
                    color = AppTheme.colors.surfaceContainer,
                    style = AppTheme.typography.labelMedium
                )
            }
        }
    }
}