package app.agent.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentsListState
import ui.component.AttributeFilterChips
import ui.component.RarityFilterChips
import ui.component.SpecialtyFilterChips
import ui.theme.AppTheme
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
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = AppTheme.colors.surfaceContainer,
        contentColor = AppTheme.colors.onSurfaceContainer
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            RarityFilterChips(uiState.selectedRarity, onRarityChipSelectionChanged)
            AttributeFilterChips(
                uiState.selectedAttributes, onAttributeChipSelectionChanged
            )
            SpecialtyFilterChips(
                uiState.selectedSpecialties, onSpecialtyChipSelectionChanged
            )
            Spacer(Modifier.size(32.dp))
        }
    }
}