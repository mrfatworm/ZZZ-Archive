/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.navigation.NAV_RAIL_MAIN_FLOW
import ui.navigation.NavActions
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.ic_moon
import zzzarchive.composeapp.generated.resources.ic_nav_back
import zzzarchive.composeapp.generated.resources.ic_sun
import zzzarchive.composeapp.generated.resources.light_theme
import zzzarchive.composeapp.generated.resources.navigation_drawer

val navigationDrawerMinWidth = 240.dp
val navigationDrawerMaxWidth = 360.dp

@Composable
fun ModalNavigationDrawerContent(
    selectedMainFlow: String,
    navigationActions: NavActions,
    onDrawerClicked: () -> Unit,
    onThemeChanged: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight().padding(AppTheme.spacing.s200)
            .clip(AppTheme.shape.r500)
            .widthIn(min = navigationDrawerMinWidth, max = navigationDrawerMaxWidth)
            .border(AppTheme.size.borderWidth, AppTheme.colors.border, AppTheme.shape.r500)
            .background(AppTheme.colors.surfaceContainer)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s450)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(AppTheme.spacing.s400),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.app_name).uppercase(),
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.onSurfaceVariant
            )
            IconButton(onClick = onDrawerClicked) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_nav_back),
                    contentDescription = stringResource(Res.string.navigation_drawer),
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NAV_RAIL_MAIN_FLOW.forEach { destination ->
                val isSelected = selectedMainFlow == destination.route
                NavigationDrawerItem(selected = isSelected, label = {
                    Text(
                        modifier = Modifier.padding(horizontal = AppTheme.spacing.s400),
                        text = stringResource(destination.textRes),
                        style = if (isSelected) AppTheme.typography.labelLarge else AppTheme.typography.labelMedium
                    )
                }, icon = {
                    Icon(
                        imageVector = vectorResource(destination.iconRes),
                        contentDescription = stringResource(destination.textRes)
                    )
                }, colors = navigationDrawerItemColors(), onClick = {
                    navigationActions.navigationToMainScreen(destination)
                    onDrawerClicked()
                })
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = AppTheme.spacing.s400),
                    thickness = AppTheme.size.borderWidth,
                    color = AppTheme.colors.border
                )
            }
            Spacer(
                modifier = Modifier.weight(1f).heightIn(min = AppTheme.spacing.s400)
            )
            val isDark by AppTheme.isDark
            NavigationDrawerItem(
                modifier = Modifier.padding(horizontal = AppTheme.spacing.s350),
                selected = false,
                onClick = onThemeChanged,
                icon = {
                    Icon(
                        imageVector = vectorResource(if (isDark) Res.drawable.ic_sun else Res.drawable.ic_moon),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(if (isDark) Res.string.light_theme else Res.string.dark_theme),
                        style = AppTheme.typography.labelMedium,
                    )
                },
                colors = navigationDrawerItemColors()
            )
        }

    }
}

@Composable
private fun navigationDrawerItemColors() = NavigationDrawerItemDefaults.colors(
    selectedContainerColor = AppTheme.colors.primaryContainer,
    selectedIconColor = AppTheme.colors.onPrimaryContainer,
    selectedTextColor = AppTheme.colors.onPrimaryContainer,
    unselectedContainerColor = Color.Transparent,
    unselectedIconColor = AppTheme.colors.onSurfaceContainer,
    unselectedTextColor = AppTheme.colors.onSurfaceContainer
)

