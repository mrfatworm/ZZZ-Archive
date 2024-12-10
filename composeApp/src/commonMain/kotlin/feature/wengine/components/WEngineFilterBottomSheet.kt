package feature.wengine.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.wengine.model.WEnginesListState
import ui.components.ZzzBottomSheet
import ui.components.chips.RarityFilterChipsList
import ui.components.chips.SpecialtyFilterChips
import ui.theme.AppTheme
import utils.AgentSpecialty
import utils.ZzzRarity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WEngineFilterBottomSheet(
    sheetState: SheetState,
    uiState: WEnginesListState,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
    onDismiss: () -> Unit
) {
    ZzzBottomSheet(sheetState = sheetState, onDismiss = onDismiss) {
        Column(
            modifier = Modifier.padding(horizontal = AppTheme.spacing.s400),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s450)
        ) {
            RarityFilterChipsList(
                selectedRarity = uiState.selectedRarity,
                onSelectionChanged = onRarityChipSelectionChanged
            )
            SpecialtyFilterChips(
                selectedSpecialty = uiState.selectedSpecialties,
                onSelectionChanged = onSpecialtyChipSelectionChanged
            )
            Spacer(Modifier.size(AppTheme.spacing.s400))
        }
    }
}