/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.setting.model.FeedbackState
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back

@Composable
fun FeedbackScreenMedium(
    feedbackState: FeedbackState, adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.contentPadding(adaptiveLayoutType, AppTheme.dimens).padding(16.dp)
    ) {
        ZzzIconButton(
            iconRes = Res.drawable.ic_arrow_back,
            contentDescriptionRes = Res.string.back,
            onClick = onBackClick
        )
        FeedbackFormCard(feedbackState)
    }

}