/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.agent.model.AgentLevelMaterial
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.HoveredIndicatorHeader
import ui.component.RarityItemMini
import ui.theme.AppTheme
import ui.utils.drawRowListMask
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
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ), state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = materialsList, key = { it.id }) { material ->
                RarityItemMini(
                    modifier = Modifier.animateItem(),
                    text = material.getAmountText(),
                    imgUrl = material.getProfileUrl()
                )
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
        }
    }
}

@Composable
private fun Header(
    isHovered: State<Boolean>,
    lazyListState: LazyListState,
    checkState: Boolean,
    onCheckChange: (Boolean) -> Unit = {}
) {
    val levelLabel = if (checkState) " Lv.12" else " Lv.10"
    HoveredIndicatorHeader(
        title = stringResource(Res.string.materials),
        isHovered = isHovered.value,
        lazyListState = lazyListState
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.skills) + levelLabel,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurfaceVariant
            )
            Switch(
                checked = checkState,
                onCheckedChange = { onCheckChange(it) },
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = AppTheme.colors.buttonBorder,
                    uncheckedBorderColor = AppTheme.colors.buttonBorder,
                    uncheckedTrackColor = Color.Transparent,
                    checkedThumbColor = Color.White,
                    checkedTrackColor = AppTheme.colors.primaryContainer
                )
            )
        }
    }
}