/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.ic_do_not_disturb_on

@Composable
fun MyAgentFooterCard(modifier: Modifier = Modifier) {
    val icon = vectorResource(Res.drawable.ic_do_not_disturb_on)
    val iconColor = AppTheme.colors.onSurfaceVariant
    val iconSize = AppTheme.size.icon
    ContentCard(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.app_name),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.headlineSmall
            )
            Icon(
                modifier = Modifier.align(Alignment.TopStart).size(iconSize),
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
            Icon(
                modifier = Modifier.align(Alignment.TopEnd).size(iconSize),
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
            Icon(
                modifier = Modifier.align(Alignment.BottomStart).size(iconSize),
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
            Icon(
                modifier = Modifier.align(Alignment.BottomEnd).size(iconSize),
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}