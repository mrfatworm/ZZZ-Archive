/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.drive.model.DrivesListState
import ui.component.ContentCard
import ui.component.RarityItem
import ui.theme.AppTheme
import ui.utils.drawColumnListMask

@Composable
fun DrivesListCard(
    modifier: Modifier = Modifier,
    uiState: DrivesListState,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    onDriveClick: (Int) -> Unit,
) {
    ContentCard(
        modifier = modifier,
        hasDefaultPadding = false,
    ) {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxSize().drawColumnListMask(
                colorScheme = AppTheme.colors,
                topEnable = lazyGridState.canScrollBackward,
                bottomEnable = lazyGridState.canScrollForward
            ),
            contentPadding = PaddingValues(AppTheme.dimens.paddingCard),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = uiState.drivesList.size,
                key = { index -> uiState.drivesList[index].id }) { index ->
                val drive = uiState.drivesList[index]
                RarityItem(modifier = Modifier.animateItem(),
                    name = drive.name,
                    imgUrl = drive.getProfileUrl(),
                    onClick = {
                        onDriveClick(drive.id)
                    })
            }
        }
    }
}