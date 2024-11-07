/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.Faction
import org.jetbrains.compose.resources.stringResource
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_award_star

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FactionFilterChipsList(
    selectedFactionId: Int, factionsList: List<Faction>, onSelectionChanged: (Int) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.dimens.paddingCard),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        factionsList.forEach { faction ->
            ZzzFilterChip(text = stringResource(faction.getFactionNameRes()),
                iconRes = Res.drawable.ic_award_star,
                selected = selectedFactionId == faction.id,
                onClick = {
                    onSelectionChanged(faction.id)
                })
        }
    }
}