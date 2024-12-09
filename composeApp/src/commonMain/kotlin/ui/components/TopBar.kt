/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZzzTopBar(
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