/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.drive.model.DrivesListState
import ui.components.cards.ContentCard
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.cardPadding
import ui.utils.drawColumnListMask
import ui.utils.gridListHorizontalGap
import ui.utils.gridListVerticalGap

@Composable
fun DrivesListCard(
    modifier: Modifier = Modifier,
    uiState: DrivesListState,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    onDriveClick: (Int) -> Unit
) {
    ContentCard(
        modifier = modifier,
        hasDefaultPadding = false,
    ) {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Adaptive(AppTheme.size.s100),
            modifier = Modifier.fillMaxSize().drawColumnListMask(
                colorScheme = AppTheme.colors,
                topEnable = lazyGridState.canScrollBackward,
                bottomEnable = lazyGridState.canScrollForward
            ),
            contentPadding = PaddingValues(cardPadding()),
            horizontalArrangement = gridListHorizontalGap(),
            verticalArrangement = gridListVerticalGap()
        ) {
            items(
                count = uiState.drivesList.size,
                key = { index -> uiState.drivesList[index].id }) { index ->
                val drive = uiState.drivesList[index]
                RarityItem(
                    modifier = Modifier.animateItem(),
                    name = drive.name,
                    imgUrl = drive.imageUrl,
                    onClick = {
                        onDriveClick(drive.id)
                    })
            }
        }
    }
}