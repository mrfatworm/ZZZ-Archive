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
import utils.AgentSpecialty

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