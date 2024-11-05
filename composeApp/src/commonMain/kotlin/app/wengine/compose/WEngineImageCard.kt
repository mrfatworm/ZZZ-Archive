/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.wengine.model.WEngineDetailResponse
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTag
import ui.components.buttons.ZzzIconButton
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import ui.utils.drawBottomMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_rare

@Composable
fun WEngineImageCard(wEngineDetail: WEngineDetailResponse, onBackClick: () -> Unit) {
    ContentCard {
        ZzzIconButton(
            iconRes = Res.drawable.ic_arrow_back, contentDescriptionRes = Res.string.back
        ) {
            onBackClick()
        }
        Box(
            modifier = Modifier.fillMaxWidth().drawBottomMask(AppTheme.colors)
        ) {
            AsyncImage(
                modifier = Modifier.widthIn(min = 120.dp, max = 240.dp).aspectRatio(1f)
                    .align(Alignment.TopCenter),
                model = wEngineDetail.getWEngineImageUrl(),
                contentDescription = null,
            )
        }

        Spacer(Modifier.size(8.dp))
        Text(
            text = wEngineDetail.name,
            style = AppTheme.typography.headlineLarge,
            color = AppTheme.colors.onSurface
        )
        Spacer(Modifier.size(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ZzzTag(text = wEngineDetail.getRarity().code, iconRes = Res.drawable.ic_rare)
            ZzzTag(
                text = stringResource(wEngineDetail.getSpecialty().textRes),
                iconRes = wEngineDetail.getSpecialty().iconRes
            )
        }
    }
}