/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.chips

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import ui.utils.conditional
import utils.AgentAttribute

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AttributeFilterChipsList(
    selectedAttributes: Set<AgentAttribute>,
    maxLine: Int = Int.MAX_VALUE,
    onSelectionChanged: (Set<AgentAttribute>) -> Unit
) {
    val attributes = AgentAttribute.entries.toTypedArray().dropLast(1)

    FlowRow(
        modifier = Modifier.fillMaxWidth().conditional(maxLine != Int.MAX_VALUE) {
            horizontalScroll(rememberScrollState())
        }.padding(horizontal = AppTheme.dimens.paddingCard),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxLines = maxLine
    ) {
        attributes.forEach { attribute ->
            ZzzFilterChip(text = stringResource(attribute.textRes),
                iconRes = attribute.iconRes,
                selected = selectedAttributes.contains(attribute),
                onClick = {
                    val newSelection = if (selectedAttributes.contains(attribute)) {
                        selectedAttributes - attribute
                    } else {
                        selectedAttributes + attribute
                    }
                    onSelectionChanged(newSelection)
                })
        }
    }
}