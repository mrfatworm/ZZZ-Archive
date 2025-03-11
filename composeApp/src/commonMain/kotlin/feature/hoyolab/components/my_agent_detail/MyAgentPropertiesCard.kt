/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.hoyolab.model.my_agent_detail.MyAgentDetailEquipPlanProperty
import feature.hoyolab.model.my_agent_detail.MyAgentDetailPropertyResponse
import ui.components.cards.ContentCard
import ui.theme.AppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyAgentPropertiesCard(
    modifier: Modifier = Modifier, properties: List<MyAgentDetailPropertyResponse>,
    planProperties: List<MyAgentDetailEquipPlanProperty>
) {
    if (properties.isEmpty()) return
    ContentCard(modifier = modifier, hasDefaultPadding = false) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                for (index in 0..5) {
                    MyAgentPropertyItem(
                        title = properties[index].propertyName,
                        value = properties[index].final,
                        highlight = planProperties.find { it.name == properties[index].propertyName } != null,
                        isVariantColor = index % 2 == 0
                    )
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                for (index in 6..11) {
                    MyAgentPropertyItem(
                        title = properties[index].propertyName,
                        value = properties[index].final,
                        highlight = planProperties.find { it.name == properties[index].propertyName } != null,
                        isVariantColor = index % 2 == 0
                    )
                }
            }
        }
    }
}

@Composable
private fun MyAgentPropertyItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    highlight: Boolean = false,
    isVariantColor: Boolean = false
) {
    val titleSmall = AppTheme.typography.titleSmall
    var titleFontSize by remember { mutableStateOf(titleSmall.fontSize) }
    Row(
        modifier = modifier.background(if (isVariantColor) AppTheme.colors.itemVariant else AppTheme.colors.surfaceContainer)
            .padding(
                horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s300
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = if (highlight) AppTheme.colors.primary else AppTheme.colors.onSurfaceVariant,
            style = titleSmall,
            maxLines = 1,
            fontSize = titleFontSize,
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    // smaller font size
                    titleFontSize = titleFontSize * 0.9
                }
            }
        )
        Text(
            text = value,
            color = if (highlight) AppTheme.colors.primary else AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.labelMedium
        )
    }
}