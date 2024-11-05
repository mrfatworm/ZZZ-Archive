package app.agent.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentsListState
import ui.components.ZzzBottomSheet
import ui.components.chips.AttributeFilterChipsList
import ui.components.chips.FactionFilterChipsList
import ui.components.chips.RarityFilterChipsList
import ui.components.chips.SpecialtyFilterChips
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentFilterBottomSheet(
    sheetState: SheetState,
    uiState: AgentsListState,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
    onFactionChipSelectionChanged: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    ZzzBottomSheet(sheetState = sheetState, onDismiss = onDismiss) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            RarityFilterChipsList(
                selectedRarity = uiState.selectedRarity,
                onSelectionChanged = onRarityChipSelectionChanged
            )
            AttributeFilterChipsList(
                selectedAttributes = uiState.selectedAttributes,
                onSelectionChanged = onAttributeChipSelectionChanged
            )
            SpecialtyFilterChips(
                selectedSpecialty = uiState.selectedSpecialties,
                onSelectionChanged = onSpecialtyChipSelectionChanged
            )
            FactionFilterChipsList(
                selectedFactionId = uiState.selectedFactionId,
                factionsList = uiState.factionsList,
                onSelectionChanged = onFactionChipSelectionChanged
            )
            Spacer(Modifier.size(16.dp))
        }
    }
}