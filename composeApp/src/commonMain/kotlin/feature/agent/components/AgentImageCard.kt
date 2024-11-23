/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import feature.agent.model.AgentDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTag
import ui.components.buttons.ZzzIconButton
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import ui.utils.drawBottomMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_award_star
import zzzarchive.composeapp.generated.resources.ic_rare

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AgentImageCard(agentDetail: AgentDetailResponse, onBackClick: () -> Unit) {
    ContentCard {
        ZzzIconButton(
            iconRes = Res.drawable.ic_arrow_back, contentDescriptionRes = Res.string.back
        ) {
            onBackClick()
        }
        Box(
            modifier = Modifier.aspectRatio(1.33f).drawBottomMask(AppTheme.colors)
                .verticalScroll(state = rememberScrollState(), enabled = false)
        ) {
            AsyncImage(
                modifier = Modifier.align(Alignment.TopCenter).fillMaxSize(0.8f),
                model = agentDetail.getFactionIconUrl(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.15f
            )
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = agentDetail.getAgentPortraitImageUrl(),
                contentDescription = null,
            )
        }
        Spacer(Modifier.size(8.dp))
        SelectionContainer {
            Text(
                text = agentDetail.fullName,
                style = AppTheme.typography.headlineLarge,
                color = AppTheme.colors.onSurface
            )
        }
        Spacer(Modifier.size(16.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ZzzTag(text = agentDetail.getRarity().code, iconRes = Res.drawable.ic_rare)
            ZzzTag(
                text = stringResource(agentDetail.getAttribute().textRes),
                iconRes = agentDetail.getAttribute().iconRes
            )
            ZzzTag(
                text = stringResource(agentDetail.getSpecialty().textRes),
                iconRes = agentDetail.getSpecialty().iconRes
            )
            ZzzTag(
                text = stringResource(agentDetail.getAttackType().textRes),
                iconRes = agentDetail.getAttackType().iconRes
            )
            ZzzTag(
                text = stringResource(agentDetail.getFactionNameRes()),
                iconRes = Res.drawable.ic_award_star
            )
        }
    }
}