/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import ui.utils.conditional
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_rare

val chipShape = RoundedCornerShape(8.dp)

@Composable
fun ZzzFilterChip(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: DrawableResource,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clip(chipShape).pointerHoverIcon(PointerIcon.Hand)
            .clickable(onClick = onClick).background(
                color = if (selected) AppTheme.colors.primaryContainer else AppTheme.colors.surface
            ).border(
                width = 1.dp,
                color = if (selected) Color.Transparent else AppTheme.colors.border,
                shape = chipShape
            ).padding(start = 8.dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = vectorResource(iconRes),
            contentDescription = null,
            tint = if (selected) AppTheme.colors.onPrimaryContainer else AppTheme.colors.onSurface
        )
        Text(
            text = text,
            color = if (selected) AppTheme.colors.onPrimaryContainer else AppTheme.colors.onSurface,
            style = AppTheme.typography.labelMedium
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RarityFilterChips(
    selectedRarity: Set<ZzzRarity>, onSelectionChanged: (Set<ZzzRarity>) -> Unit
) {
    val rarities = listOf(ZzzRarity.RANK_S, ZzzRarity.RANK_A)
    FlowRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.dimens.paddingCard),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rarities.forEach { rarity ->
            ZzzFilterChip(text = rarity.code,
                iconRes = Res.drawable.ic_rare,
                selected = selectedRarity.contains(rarity),
                onClick = {
                    val newSelection = if (selectedRarity.contains(rarity)) {
                        selectedRarity - rarity
                    } else {
                        selectedRarity + rarity
                    }
                    onSelectionChanged(newSelection)
                })
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AttributeFilterChips(
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpecialtyFilterChips(
    selectedSpecialty: Set<AgentSpecialty>,
    maxLine: Int = Int.MAX_VALUE,
    onSelectionChanged: (Set<AgentSpecialty>) -> Unit
) {
    val specialties = AgentSpecialty.entries.toTypedArray().dropLast(1)

    FlowRow(
        modifier = Modifier.fillMaxWidth().conditional(maxLine != Int.MAX_VALUE) {
            horizontalScroll(rememberScrollState())
        }.padding(horizontal = AppTheme.dimens.paddingCard),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        specialties.forEach { specialty ->
            ZzzFilterChip(text = stringResource(specialty.textRes),
                iconRes = specialty.iconRes,
                selected = selectedSpecialty.contains(specialty),
                onClick = {
                    val newSelection = if (selectedSpecialty.contains(specialty)) {
                        selectedSpecialty - specialty
                    } else {
                        selectedSpecialty + specialty
                    }
                    onSelectionChanged(newSelection)
                })
        }
    }
}