/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.navigation.NavActions
import ui.navigation.TOP_LEVEL_DESTINATIONS_NAV_RAIL
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.ic_moon
import zzzarchive.composeapp.generated.resources.ic_nav_back
import zzzarchive.composeapp.generated.resources.ic_sun
import zzzarchive.composeapp.generated.resources.light_theme
import zzzarchive.composeapp.generated.resources.navigation_drawer

val navigationDrawerShape = RoundedCornerShape(32.dp)
val navigationDrawerMinWidth = 240.dp
val navigationDrawerMaxWidth = 360.dp

@Composable
fun ModalNavigationDrawerContent(
    selectedDestination: String, navigationActions: NavActions, onDrawerClicked: () -> Unit, onThemeChanged: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight().padding(4.dp)
            .widthIn(min = navigationDrawerMinWidth, max = navigationDrawerMaxWidth)
            .border(3.dp, AppTheme.colors.border, navigationDrawerShape)
            .background(AppTheme.colors.surfaceContainer, navigationDrawerShape)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
                    painter = painterResource(Res.drawable.ic_nav_back),
                    contentDescription = stringResource(Res.string.navigation_drawer),
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TOP_LEVEL_DESTINATIONS_NAV_RAIL.forEach { destination ->
                val isSelected = selectedDestination == destination.route
                NavigationDrawerItem(selected = isSelected, label = {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(destination.textRes),
                        style = if (isSelected) AppTheme.typography.labelLarge else AppTheme.typography.labelMedium
                    )
                }, icon = {
                    Icon(
                        imageVector = vectorResource(destination.iconRes),
                        contentDescription = stringResource(destination.textRes)
                    )
                }, colors = navigationDrawerItemColors(), onClick = {
                    navigationActions.navigationToTop(destination)
                    onDrawerClicked()
                })
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 3.dp,
                    color = AppTheme.colors.border
                )
            }
            Spacer(
                modifier = Modifier.weight(1f).heightIn(min = 16.dp)
            )
            val isDark by AppTheme.isDark
            NavigationDrawerItem(
                modifier = Modifier.padding(horizontal = 12.dp),
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
    selectedContainerColor = AppTheme.colors.primary,
    selectedIconColor = AppTheme.colors.onPrimary,
    selectedTextColor = AppTheme.colors.onPrimary,
    unselectedContainerColor = Color.Transparent,
    unselectedIconColor = AppTheme.colors.onSurfaceContainer,
    unselectedTextColor = AppTheme.colors.onSurfaceContainer
)

