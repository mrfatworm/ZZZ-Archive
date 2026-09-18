/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.AsyncImage
import feature.hoyolab.model.agent.MyAgentDetailEquip
import feature.hoyolab.model.agent.MyAgentEquipSuit
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.hit

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyAgentDrivesCard(
    modifier: Modifier = Modifier,
    drives: List<MyAgentDetailEquip>
) {
    if (drives.isEmpty()) return
    val openedSuit = remember { mutableStateOf<MyAgentEquipSuit.EquipSuit?>(null) }
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        maxItemsInEachRow = 3,
        maxLines = 3,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (drive in drives) {
            MyAgentDriveItem(
                modifier = Modifier.weight(1f).widthIn(min = 160.dp),
                drive = drive,
                onSuitClick = { openedSuit.value = it }
            )
        }
    }

    openedSuit.value?.let { suit ->
        MyAgentDriveSuitDialog(suit = suit) { openedSuit.value = null }
    }
}

@Composable
private fun MyAgentDriveItem(
    modifier: Modifier = Modifier,
    drive: MyAgentDetailEquip,
    onSuitClick: (MyAgentEquipSuit.EquipSuit) -> Unit
) {
    val suit = drive.equipSuit
    ContentCard(
        modifier = modifier.width(IntrinsicSize.Min),
        hasDefaultPadding = false,
        onClick = if (suit is MyAgentEquipSuit.EquipSuit) {
            { onSuitClick(suit) }
        } else {
            null
        }
    ) {
        Column(modifier = Modifier) {
            MyAgentDriveHeader(drive)
            drive.mainProperties.firstOrNull()?.let { mainProperty ->
                MyAgentDriveMainPropertyItem(
                    title = mainProperty.name,
                    value = mainProperty.base
                )
            }
            for (subProperty in drive.subProperties) {
                MyAgentDriveSubPropertyItem(
                    title = subProperty.name,
                    value = subProperty.base,
                    highlight = subProperty.valid
                )
            }
        }
    }
}

@Composable
private fun MyAgentDriveHeader(drive: MyAgentDetailEquip) {
    // `level` is the roll count of a sub stat (initial roll + upgrades), so summing it over the
    // valid ones reproduces HoYoLab's own equip_plan_info.valid_property_cnt across the six discs.
    val totalHit = remember(drive) { drive.subProperties.filter { it.valid }.sumOf { it.level } }

    Row {
        AsyncImage(
            modifier = Modifier.size(AppTheme.size.s64),
            uri = drive.iconUrl,
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(AppTheme.spacing.s300),
            verticalArrangement =
                Arrangement.spacedBy(
                    AppTheme.spacing.s400
                )
        ) {
            Text(
                text = drive.name,
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.titleSmall,
                maxLines = 1
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lv ${drive.level}",
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelSmall
                )

                Text(
                    modifier =
                        Modifier
                            .clip(AppTheme.shape.r200)
                            .background(AppTheme.colors.onSurfaceVariant)
                            .padding(
                                horizontal = AppTheme.spacing.s200,
                                vertical = AppTheme.spacing.s100
                            ),
                    text = stringResource(Res.string.hit, totalHit),
                    color = AppTheme.colors.surfaceContainer,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun MyAgentDriveMainPropertyItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    val titleSmall = AppTheme.typography.titleSmall
    // Keyed on what it was measured for: the size only ever shrinks, so an unkeyed remember would
    // hold a reused slot at the smaller size after the disc -- or the user's font scale -- changed.
    var titleFontSize by remember(title, titleSmall.fontSize) { mutableStateOf(titleSmall.fontSize) }
    Row(
        modifier =
            modifier.background(AppTheme.colors.surfaceContainer).padding(
                horizontal = AppTheme.spacing.s350,
                vertical = AppTheme.spacing.s300
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.titleSmall,
            fontSize = titleFontSize,
            maxLines = 1,
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    // smaller font size
                    titleFontSize = titleFontSize * 0.9
                }
            }
        )
        Text(
            text = value,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.labelMedium
        )
    }
}

@Composable
private fun MyAgentDriveSubPropertyItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    highlight: Boolean = false
) {
    Row(
        modifier =
            modifier.background(AppTheme.colors.surfaceContainer).padding(
                horizontal = AppTheme.spacing.s350,
                vertical = AppTheme.spacing.s200
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = if (highlight) AppTheme.colors.rarityS else AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.bodyMedium,
            maxLines = 1
        )
        Text(
            text = value,
            color = if (highlight) AppTheme.colors.rarityS else AppTheme.colors.onSurfaceVariant,
            style = if (highlight) AppTheme.typography.labelMedium else AppTheme.typography.bodyMedium
        )
    }
}
