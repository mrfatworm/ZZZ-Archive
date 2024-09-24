/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.navigation.NavActions
import ui.navigation.TOP_LEVEL_DESTINATIONS_COMPACT
import ui.theme.AppTheme

@Composable
fun ZzzArchiveBottomNavigationBar(
    selectedDestination: String, navigationActions: NavActions
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(), containerColor = AppTheme.colors.surface
    ) {
        TOP_LEVEL_DESTINATIONS_COMPACT.forEach { destination ->
            val isSelected = selectedDestination == destination.route
            NavigationBarItem(selected = isSelected, onClick = {
                if (isSelected) {
                    navigationActions.navigationToTop(destination)
                } else {
                    navigationActions.navigationToTopAndSave(destination)
                }
            }, icon = {
                Icon(
                    imageVector = vectorResource(destination.iconRes),
                    contentDescription = stringResource(destination.textRes)
                )
            }, label = {
                Text(
                    text = stringResource(destination.textRes),
                    style = if (isSelected) AppTheme.typography.labelMedium else AppTheme.typography.labelSmall
                )
            }, colors = navigationBarItemColors()
            )
        }
    }
}

@Composable
private fun navigationBarItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = AppTheme.colors.onPrimaryContainer,
    selectedTextColor = AppTheme.colors.onSurface,
    indicatorColor = AppTheme.colors.primaryContainer,
    unselectedIconColor = AppTheme.colors.onSurfaceVariant,
    unselectedTextColor = AppTheme.colors.onSurfaceVariant
)