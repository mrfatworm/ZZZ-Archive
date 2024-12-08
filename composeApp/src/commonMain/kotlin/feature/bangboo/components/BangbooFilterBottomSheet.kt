package feature.bangboo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.bangboo.model.BangbooListState
import ui.components.ZzzBottomSheet
import ui.components.chips.AttributeFilterChipsList
import ui.components.chips.RarityFilterChipsList
import ui.theme.AppTheme
import utils.AgentAttribute
import utils.ZzzRarity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangbooFilterBottomSheet(
    sheetState: SheetState,
    uiState: BangbooListState,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onDismiss: () -> Unit
) {
    ZzzBottomSheet(sheetState = sheetState, onDismiss = onDismiss) {
        Column(
            modifier = Modifier.padding(horizontal = AppTheme.spacing.s300),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s450)
        ) {
            RarityFilterChipsList(
                selectedRarity = uiState.selectedRarity,
                onSelectionChanged = onRarityChipSelectionChanged
            )
            AttributeFilterChipsList(
                selectedAttributes = uiState.selectedAttributes,
                onSelectionChanged = onAttributeChipSelectionChanged
            )
            Spacer(Modifier.size(AppTheme.spacing.s400))
        }
    }
}