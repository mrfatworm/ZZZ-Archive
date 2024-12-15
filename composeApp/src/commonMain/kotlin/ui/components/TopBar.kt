/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScaffold(
    title: String? = null,
    hasBack: Boolean = true,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(title = {
        title?.let {
            Text(
                text = title, style = AppTheme.typography.titleLarge
            )
        }
    }, navigationIcon = {
        if (hasBack) {
            ZzzIconButton(
                modifier = Modifier.padding(start = AppTheme.spacing.s400),
                iconRes = Res.drawable.ic_arrow_back,
                contentDescriptionRes = Res.string.back,
                onClick = onBackClick
            )
        }
    }, actions = {
        actions()
        Spacer(Modifier.size(AppTheme.spacing.s400))
    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = AppTheme.colors.surfaceContainer,
        navigationIconContentColor = AppTheme.colors.onSurfaceContainer,
        titleContentColor = AppTheme.colors.onSurfaceContainer,
        actionIconContentColor = AppTheme.colors.onSurfaceContainer,
    )
    )
}

@Composable
fun TopBarRound(
    title: String,
    hasBack: Boolean = true,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth().clip(CircleShape)
            .background(AppTheme.colors.surfaceContainer)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s300)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.titleLarge
        )
        if (hasBack) {
            ZzzIconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                iconRes = Res.drawable.ic_arrow_back,
                contentDescriptionRes = Res.string.back,
                onClick = onBackClick
            )
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }
    }
}