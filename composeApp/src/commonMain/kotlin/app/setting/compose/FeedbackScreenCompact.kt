/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.setting.model.FeedbackState
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTopBar
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.feedback

@Composable
fun FeedbackScreenCompact(
    feedbackState: FeedbackState, onBackClick: () -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        ZzzTopBar(
            title = stringResource(Res.string.feedback), onBackClick = onBackClick
        )

    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
                .padding(AppTheme.dimens.paddingParentCompact)
        ) {
            FeedbackFormCard(feedbackState)
        }
    }
}