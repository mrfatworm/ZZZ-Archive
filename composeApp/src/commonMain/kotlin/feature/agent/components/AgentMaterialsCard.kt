/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.agent.model.AgentLevelMaterial
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzSwitch
import ui.components.cards.ContentCard
import ui.components.cards.HoveredIndicatorHeader
import ui.components.items.RarityMiniItem
import ui.theme.AppTheme
import ui.utils.cardPaddingWithHeader
import ui.utils.rowListGap
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.materials
import zzzarchive.composeapp.generated.resources.skills

@Composable
fun AgentMaterialsCard(material: AgentLevelMaterial) {
    var checkState by remember { mutableStateOf(false) }
    val materialsList = if (checkState) material.skillMax else material.skillTen
    val lazyListState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    ContentCard(
        modifier = Modifier.hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        Header(isHovered, lazyListState, checkState) {
            checkState = it
        }
        LazyRow(
            state = lazyListState,
            contentPadding = cardPaddingWithHeader(),
            horizontalArrangement = rowListGap()
        ) {
            items(items = materialsList, key = { it.id }) { material ->
                RarityMiniItem(
                    modifier = Modifier.animateItem(),
                    text = material.getAmountText(),
                    imgUrl = material.getProfileUrl()
                )
            }
        }
    }
}

@Composable
private fun Header(
    isHovered: State<Boolean>,
    lazyListState: LazyListState,
    checkState: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    val levelLabel = if (checkState) " Lv.12" else " Lv.10"
    HoveredIndicatorHeader(
        title = stringResource(Res.string.materials),
        isHovered = isHovered.value,
        lazyListState = lazyListState
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.skills) + levelLabel,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurfaceVariant
            )
            ZzzSwitch(checkState, onCheckChange)
        }
    }
}