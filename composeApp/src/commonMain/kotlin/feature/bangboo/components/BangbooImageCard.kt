/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import feature.bangboo.model.BangbooDetail
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTag
import ui.components.buttons.ZzzIconButton
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_rare

@Composable
fun BangbooImageCard(bangbooDetail: BangbooDetail, onBackClick: () -> Unit) {
    ContentCard {
        ZzzIconButton(
            iconRes = Res.drawable.ic_arrow_back, contentDescriptionRes = Res.string.back
        ) {
            onBackClick()
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier.widthIn(min = 120.dp, max = 320.dp).aspectRatio(1f)
                    .align(Alignment.TopCenter),
                model = bangbooDetail.imageUrl,
                contentDescription = null,
            )
        }

        Spacer(Modifier.size(AppTheme.spacing.s300))
        SelectionContainer {
            Text(
                text = bangbooDetail.name,
                style = AppTheme.typography.headlineLarge,
                color = AppTheme.colors.onSurface
            )
        }
        Spacer(Modifier.size(AppTheme.spacing.s400))
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            ZzzTag(text = bangbooDetail.rarity.code, iconRes = Res.drawable.ic_rare)
            ZzzTag(
                text = stringResource(bangbooDetail.attribute.textRes),
                iconRes = bangbooDetail.attribute.iconRes
            )
        }
    }
}