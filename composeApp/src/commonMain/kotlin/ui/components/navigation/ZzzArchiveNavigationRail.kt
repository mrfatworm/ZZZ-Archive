/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.navigation.NAV_RAIL_MAIN_FLOW
import ui.navigation.NavActions
import ui.theme.AppTheme
import ui.utils.verticalSafePadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.ic_arrow_back_ios
import zzzarchive.composeapp.generated.resources.ic_moon
import zzzarchive.composeapp.generated.resources.ic_nav
import zzzarchive.composeapp.generated.resources.ic_sun
import zzzarchive.composeapp.generated.resources.light_theme
import zzzarchive.composeapp.generated.resources.navigation_drawer

@Composable
fun ZzzArchiveNavigationRail(
    modifier: Modifier,
    selectedMainFlow: NavDestination?,
    navActions: NavActions,
    canNavigateBack: Boolean,
    onDrawerClicked: () -> Unit,
    onBackClick: () -> Unit,
    onThemeChanged: () -> Unit
) {
    Column(
        modifier
            .background(color = AppTheme.colors.surfaceContainer, shape = AppTheme.shape.r200)
            .widthIn(min = 80.dp)
            .padding(verticalSafePadding())
            .selectableGroup(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
    ) {
        NavigationRailItem(
            selected = false,
            onClick = if (canNavigateBack) onBackClick else onDrawerClicked,
            icon = {
                Icon(
                    imageVector = vectorResource(
                        if (canNavigateBack) Res.drawable.ic_arrow_back_ios else Res.drawable.ic_nav
                    ),
                    contentDescription = stringResource(
                        if (canNavigateBack) Res.string.back else Res.string.navigation_drawer
                    ),
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
        )
        Spacer(Modifier.height(AppTheme.spacing.s300))
        Column(
            modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
        ) {
            NAV_RAIL_MAIN_FLOW.forEach { mainFlow ->
                val isSelected = selectedMainFlow?.hierarchy?.any {
                    it.hasRoute(route = mainFlow.route::class)
                } == true
                NavigationRailItem(
                    selected = isSelected,
                    onClick = {
                        navActions.navigationToMainScreen(mainFlow.route)
                    },
                    icon = {
                        Icon(
                            imageVector = vectorResource(mainFlow.iconRes),
                            contentDescription = stringResource(mainFlow.textRes)
                        )
                    },
                    colors = navigationRailItemColors()
                )
            }
            Spacer(
                modifier = Modifier.weight(1f).heightIn(min = AppTheme.spacing.s400)
            )

            val isDark = AppTheme.isDark
            NavigationRailItem(selected = false, onClick = {
                onThemeChanged()
            }, icon = {
                Icon(
                    imageVector = vectorResource(if (isDark) Res.drawable.ic_sun else Res.drawable.ic_moon),
                    contentDescription = stringResource(if (isDark) Res.string.light_theme else Res.string.dark_theme),
                    tint = AppTheme.colors.onSurfaceVariant
                )
            })
        }
    }
}

@Composable
private fun navigationRailItemColors() = NavigationRailItemDefaults.colors(
    selectedIconColor = AppTheme.colors.onPrimaryContainer,
    selectedTextColor = AppTheme.colors.onSurfaceContainer,
    indicatorColor = AppTheme.colors.primaryContainer,
    unselectedIconColor = AppTheme.colors.onSurfaceVariant,
    unselectedTextColor = AppTheme.colors.onSurfaceVariant
)
